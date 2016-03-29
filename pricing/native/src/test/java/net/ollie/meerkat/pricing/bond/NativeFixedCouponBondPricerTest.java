package net.ollie.meerkat.pricing.bond;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import net.ollie.meerkat.IntegrationTest;
import net.ollie.meerkat.calculate.fx.ExchangeRateCalculator;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.identifier.currency.CurrencyIso;
import net.ollie.meerkat.identifier.currency.USD;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.CompoundFixedInterestRate;
import net.ollie.meerkat.numeric.interest.FixedInterestRate;
import net.ollie.meerkat.numeric.interest.InterestRate;
import net.ollie.meerkat.numeric.interest.SimpleFixedInterestRate;
import net.ollie.meerkat.numeric.interest.daycount.ActualFixedAccrualFactor;
import net.ollie.meerkat.numeric.interest.daycount.FixedFixedAccrualFactor;
import net.ollie.meerkat.numeric.money.DecimalMoney;
import net.ollie.meerkat.numeric.money.ExchangeRate;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.FixedCouponBond;
import net.ollie.meerkat.security.bond.call.BondCall;
import net.ollie.meerkat.security.bond.dates.MaturingBondDates;
import net.ollie.meerkat.temporal.interim.Interval;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author ollie
 */
public class NativeFixedCouponBondPricerTest {

    private NativeFixedCouponBondPricer testPricer;
    @Mock
    private Function<LocalDate, ExchangeRateCalculator> mockExchangeRates;
    @Mock
    private BiFunction<LocalDate, CurrencyId, InterestRate> mockDiscountRates;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        testPricer = new NativeFixedCouponBondPricer(mockExchangeRates, mockDiscountRates);
    }

    @Test
    @Category(IntegrationTest.class)
    public void testExample() {

        final LocalDate issueDate = LocalDate.of(2015, Month.JANUARY, 15);
        final List<LocalDate> couponDates = IntStream.range(1, 11)
                .mapToObj(i -> Period.ofMonths(i * 6))
                .map(p -> issueDate.plus(p))
                .collect(Collectors.toList());
        final LocalDate maturityDate = couponDates.get(couponDates.size() - 1);

        final USD usd = CurrencyIso.USD;

        final ExchangeRateCalculator mockFxCalculator = mock(ExchangeRateCalculator.class);
        final ExchangeRate<USD, USD> mockUsdFxRate = mock(ExchangeRate.class);
        when(mockUsdFxRate.convert(any(Money.class))).thenAnswer(i -> (Money) i.getArguments()[0]);
        when(mockFxCalculator.rate(usd, usd)).thenReturn(mockUsdFxRate);
        when(mockExchangeRates.apply(any(LocalDate.class))).thenReturn(mockFxCalculator);

        final InterestRate discountRate = new CompoundFixedInterestRate(new Percentage(5), ActualFixedAccrualFactor.ACT_365, 1);
        when(mockDiscountRates.apply(any(LocalDate.class), eq(usd))).thenReturn(discountRate);

        final MaturingBondDates dates = new MaturingBondDates(issueDate, maturityDate);
        final FixedInterestRate rate = new SimpleFixedInterestRate(new Percentage(2.5), FixedFixedAccrualFactor.THIRTY_THREESIXTY);
        final Money<USD> par = DecimalMoney.valueOf(usd, 100);
        final Money<USD> coupon = DecimalMoney.valueOf(usd, 3);
        final BondCall call = mock(BondCall.class);
        final FixedCouponBond bond = new FixedCouponBond("name", par, dates, coupon, rate, couponDates, call);

        //When - priced at issue
        {
            final BondPrice<USD> price = testPricer.price(issueDate, bond, usd);
            System.out.println(price.cleanFlow(new Interval(issueDate, maturityDate)));
            assertThat(price.cleanValue().doubleValue(), closeTo(104.03, 1e-8));
            assertThat(price.dirtyValue().doubleValue(), closeTo(104.03, 1e-8));
        }

        //When - priced 2.25 years in
        {
            final BondPrice<USD> price = testPricer.price(issueDate.plusMonths(27), bond, usd);
            assertThat(price.cleanValue().doubleValue(), closeTo(102.53, 1e-8));
            assertThat(price.dirtyValue().doubleValue(), closeTo(104.03, 1e-8));
        }

    }

}
