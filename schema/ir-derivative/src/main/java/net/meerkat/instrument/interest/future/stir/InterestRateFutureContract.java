package net.meerkat.instrument.interest.future.stir;

import java.time.Month;
import java.time.Period;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.instrument.Instrument;
import net.meerkat.money.Money;
import net.meerkat.utils.HasName;
import net.ollie.goat.collection.Sets;

/**
 *
 * @author ollie
 */
public interface InterestRateFutureContract<C extends CurrencyId>
        extends Instrument, HasName, HasCurrencyId {

    @Nonnull
    Money<C> notional();

    @Override
    default C currencyId() {
        return this.notional().currencyId();
    }

    @Nonnull
    Period term();

    @Nonnull
    Set<Month> deliveryMonths();

    Set<Month> MAR_JUN_SEP_DEC = Collections.unmodifiableSet(Sets.asSet(Month.MARCH, Month.JUNE, Month.SEPTEMBER, Month.DECEMBER));
    Set<Month> ALL_MONTHS = EnumSet.allOf(Month.class);

}
