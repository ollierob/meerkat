package net.ollie.meerkat.numeric.interest;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.ollie.goat.currency.CurrencyId;
import net.ollie.goat.numeric.percentage.DecimalPercentage;
import net.ollie.goat.money.DecimalMoney;
import net.ollie.goat.money.Money;
import net.ollie.goat.date.years.FractionalYears;
import net.ollie.goat.money.interest.daycount.AccrualFactor;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class SimpleFixedInterestRateTest {

    @Mock
    private CurrencyId mockCurrency;

    @Mock
    private AccrualFactor mockFactor;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAccrue() {

        final SimpleFixedInterestRate rate = new SimpleFixedInterestRate(new DecimalPercentage(8), mockFactor);

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
