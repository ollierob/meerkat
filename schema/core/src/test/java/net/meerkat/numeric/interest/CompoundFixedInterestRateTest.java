package net.meerkat.numeric.interest;

import java.time.LocalDate;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.BigDecimalMoney;
import net.meerkat.money.Money;
import net.meerkat.money.interest.fixed.CompoundFixedInterestRate;
import net.ollie.goat.numeric.percentage.BigDecimalPercentage;
import net.ollie.goat.temporal.date.count.FixedFixedDateArithmetic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import org.junit.jupiter.api.Test;
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
        final CompoundFixedInterestRate rate = new CompoundFixedInterestRate(
                new BigDecimalPercentage(5),
                FixedFixedDateArithmetic.THIRTY_360,
                1);
        final Money amount = BigDecimalMoney.valueOf(currency, 1200);
        final LocalDate start = LocalDate.now();
        final LocalDate end = start.plusYears(10);
        assertThat(rate.discount(amount, start, end).amount().doubleValue(), closeTo(736.7, 1e-2));
    }

    @Test
    public void shouldDiscount_Quarterly() {
        final CompoundFixedInterestRate rate = new CompoundFixedInterestRate(
                new BigDecimalPercentage(5),
                FixedFixedDateArithmetic.THIRTY_360,
                4);
        final Money amount = BigDecimalMoney.valueOf(currency, 1200);
        final LocalDate start = LocalDate.now();
        final LocalDate end = start.plusYears(10);
        assertThat(rate.discount(amount, start, end).amount().doubleValue(), closeTo(730.1, 1e-2));
    }

}
