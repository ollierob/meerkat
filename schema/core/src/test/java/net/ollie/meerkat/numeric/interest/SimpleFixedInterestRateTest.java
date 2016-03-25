package net.ollie.meerkat.numeric.interest;

import java.math.BigDecimal;

import org.apache.commons.math3.fraction.Fraction;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.daycount.AccrualFactor;
import net.ollie.meerkat.numeric.money.DecimalMoney;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
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

        final SimpleFixedInterestRate rate = new SimpleFixedInterestRate(new Percentage(8), mockFactor);
        final Money money = new DecimalMoney(mockCurrency, BigDecimal.ONE);

        {
            final Money accrued = rate.accrue(money, Fraction.ONE);
            assertThat(accrued, is(new DecimalMoney(mockCurrency, new BigDecimal("1.08"))));
        }

        {
            final Money accrued = rate.accrue(money, new Fraction(3, 2));
            assertThat(accrued, is(new DecimalMoney(mockCurrency, new BigDecimal("1.12"))));
        }

        {
            final Money accrued = rate.accrue(money, Fraction.TWO);
            assertThat(accrued, is(new DecimalMoney(mockCurrency, new BigDecimal("1.16"))));
        }

    }

}
