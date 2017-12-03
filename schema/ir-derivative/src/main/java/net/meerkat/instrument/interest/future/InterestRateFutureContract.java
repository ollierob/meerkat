package net.meerkat.instrument.interest.future;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.instrument.Instrument;
import net.meerkat.money.Money;
import net.meerkat.temporal.date.months.Months;

import javax.annotation.Nonnull;
import java.time.Month;

/**
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

    Months MAR_JUN_SEP_DEC = Months.of(Month.MARCH, Month.JUNE, Month.SEPTEMBER, Month.DECEMBER);
    Months ALL_MONTHS = Months.all();

}
