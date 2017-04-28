package net.meerkat.instrument.interest.future;

import java.time.Month;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.instrument.Instrument;
import net.meerkat.money.Money;
import net.ollie.goat.temporal.date.months.AllMonths;
import net.ollie.goat.temporal.date.months.Months;
import net.ollie.goat.temporal.date.months.SomeMonths;

/**
 *
 * @author ollie
 */
public interface InterestRateFutureContract<C extends CurrencyId> extends Instrument, HasCurrencyId {

    @Nonnull
    Money<C> notional();

    @Override
    default C currencyId() {
        return this.notional().currencyId();
    }

    @Nonnull
    Months deliveryMonths();

    Months MAR_JUN_SEP_DEC = new SomeMonths(Month.MARCH, Month.JUNE, Month.SEPTEMBER, Month.DECEMBER);
    Months ALL_MONTHS = new AllMonths();

}
