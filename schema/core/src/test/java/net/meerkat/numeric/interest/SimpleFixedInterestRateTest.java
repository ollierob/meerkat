package net.meerkat.numeric.interest;

import java.math.BigDecimal;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.DecimalMoney;
import net.meerkat.money.Money;
import net.meerkat.money.interest.fixed.SimpleFixedInterestRate;
import net.ollie.goat.numeric.percentage.DecimalPercentage;
import net.ollie.goat.temporal.date.count.DateArithmetic;
import net.ollie.goat.temporal.date.years.FractionalYears;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

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

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        when(mockCurrency.format()).thenReturn(money -> "$" + money.amount());
    }

    @Test
    public void testAccrue() {

        final SimpleFixedInterestRate rate = new SimpleFixedInterestRate(
                new DecimalPercentage(8),
                mockFactor);

        final Money money = new DecimalMoney(mockCurrency, BigDecimal.ONE);

        {
            final Money accrued = rate.accrue(money, FractionalYears.ONE);
            assertThat(accrued, is(new DecimalMoney(mockCurrency, new BigDecimal("1.08"))));
        }

        {
            final Money accrued = rate.accrue(money, FractionalYears.of(3, 2));
            assertThat(accrued, is(new DecimalMoney(mockCurrency, new BigDecimal("1.12"))));
        }

        {
            final Money accrued = rate.accrue(money, FractionalYears.of(2, 1));
            assertThat(accrued, is(new DecimalMoney(mockCurrency, new BigDecimal("1.16"))));
        }

    }

}
