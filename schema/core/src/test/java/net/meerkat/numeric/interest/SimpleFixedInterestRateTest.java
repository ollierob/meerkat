package net.meerkat.numeric.interest;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.BigDecimalMoney;
import net.meerkat.money.Money;
import net.meerkat.money.interest.fixed.SimpleFixedInterestRate;
import net.meerkat.numeric.percentage.BigDecimalPercentage;
import net.meerkat.temporal.date.count.DateArithmetic;
import net.meerkat.temporal.date.years.FractionalYears;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class SimpleFixedInterestRateTest {

    @Mock
    private CurrencyId mockCurrency;

    @Mock
    private DateArithmetic mockFactor;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        when(mockCurrency.format()).thenReturn(money -> "$" + money.amount());
    }

    @Test
    public void testAccrue() {

        final SimpleFixedInterestRate rate = new SimpleFixedInterestRate(
                new BigDecimalPercentage(8),
                mockFactor);

        final Money money = new BigDecimalMoney(mockCurrency, BigDecimal.ONE);

        {
            final Money accrued = rate.accrue(money, FractionalYears.ONE);
            assertThat(accrued, is(new BigDecimalMoney(mockCurrency, new BigDecimal("1.08"))));
        }

        {
            final Money accrued = rate.accrue(money, FractionalYears.of(3, 2));
            assertThat(accrued, is(new BigDecimalMoney(mockCurrency, new BigDecimal("1.12"))));
        }

        {
            final Money accrued = rate.accrue(money, FractionalYears.of(2, 1));
            assertThat(accrued, is(new BigDecimalMoney(mockCurrency, new BigDecimal("1.16"))));
        }

    }

}
