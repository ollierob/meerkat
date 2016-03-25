package net.ollie.risk.risk.pricing.bond;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import net.ollie.meerkat.calculate.fx.ExchangeRateCalculator;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.ExchangeRate;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.PerpetualBond;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class PerpetualBondPricerTest {

    @Mock
    private ExchangeRateCalculator mockFxCalculator;
    @Mock
    private PerpetualBond mockBond;
    @Mock
    private C mockCurrency;

    private LocalDate today = LocalDate.now();

    private PerpetualBondPricer testPricer;

    @Before
    public void before() {

        MockitoAnnotations.initMocks(this);
        testPricer = new PerpetualBondPricer(date -> mockFxCalculator);

        final ExchangeRate<C, C> selfRate = mock(ExchangeRate.class);
        when(selfRate.convert(any(Money.class))).thenAnswer(i -> (Money) i.getArguments()[0]);
        when(mockFxCalculator.rate(mockCurrency, mockCurrency)).thenReturn(selfRate);

    }

    @Test
    public void shouldPriceUnshiftedReporting() {

//        final PerpetualBond.PerpetualBondNominal mockNominal = mock(PerpetualBond.PerpetualBondNominal.class);
//        final Money mockPar = mockCurrency(mockCurrency);
//        when(mockNominal.par()).thenReturn(mockPar);
//        when(mockBond.nominal()).thenReturn(mockNominal);
//
//        final PerpetualBond.PerpetualBondCoupons mockCoupons = mock(PerpetualBond.PerpetualBondCoupons.class);
//        final Money mockRecurringAmount = mockCurrency(mockCurrency);
//        when(mockCoupons.recurringAmount()).thenReturn(mockRecurringAmount);
//        final FixedInterestRate mockRecurringRate = mock(FixedInterestRate.class);
//        when(mockCoupons.recurringRate()).thenReturn(mockRecurringRate);
//        when(mockBond.coupons()).thenReturn(mockCoupons);
//        
//        final Percentage mockPercentage = mock(Percentage.class);
//        when(mockRecurringRate.annualRate()).thenReturn(mockPercentage);
//        when(mockRecurringAmount.over(mockPercentage)).thenReturn()
//
//        final BondPrice<CurrencyId> price = testPricer.price(today, mockBond, mockCurrency);

    }

    private static class C implements CurrencyId {

    }

    private static <C extends CurrencyId> Money<C> mockCurrency(final C currency) {
        final Money<C> mockMoney = mock(Money.class);
        when(mockMoney.currencyId()).thenReturn(currency);
        return mockMoney;
    }

}
