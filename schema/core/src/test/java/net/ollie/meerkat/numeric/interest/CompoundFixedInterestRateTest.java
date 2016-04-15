package net.ollie.meerkat.numeric.interest;

import java.time.LocalDate;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.time.daycount.FixedFixedAccrualFactor;
import net.ollie.meerkat.numeric.money.DecimalMoney;
import net.ollie.meerkat.numeric.money.Money;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mockito.Mockito.mock;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class CompoundFixedInterestRateTest {

    static final CurrencyId currency = mock(CurrencyId.class);

    @Test
    public void shouldAccrue() {
    }

    @Test
    public void shouldDiscount_Annual() {
        final CompoundFixedInterestRate rate = new CompoundFixedInterestRate(new Percentage(5), FixedFixedAccrualFactor.THIRTY_THREESIXTY, 1);
        final Money amount = DecimalMoney.valueOf(currency, 1200);
        final LocalDate start = LocalDate.now();
        final LocalDate end = start.plusYears(10);
        assertThat(rate.discount(amount, start, end).amount().doubleValue(), closeTo(736.7, 1e-2));
    }

    @Test
    public void shouldDiscount_Quarterly() {
        final CompoundFixedInterestRate rate = new CompoundFixedInterestRate(new Percentage(5), FixedFixedAccrualFactor.THIRTY_THREESIXTY, 4);
        final Money amount = DecimalMoney.valueOf(currency, 1200);
        final LocalDate start = LocalDate.now();
        final LocalDate end = start.plusYears(10);
        assertThat(rate.discount(amount, start, end).amount().doubleValue(), closeTo(730.1, 1e-2));
    }

}
