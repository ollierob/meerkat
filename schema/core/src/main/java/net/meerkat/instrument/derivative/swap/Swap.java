package net.meerkat.instrument.derivative.swap;

import javax.annotation.Nonnull;

import net.coljate.list.List;
import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.identifier.currency.HasCurrencyIds;
import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.Security;

/**
 *
 * @author Ollie
 */
public interface Swap extends Security, InstrumentDefinition, HasCurrencyIds {

    @Nonnull
    List<? extends SwapLeg<?, ?>> legs();

    default boolean isBullet() {
        return this.legs().count() == 1;
    }

    @Override
    default CurrencyIds currencyIds() {
        CurrencyIds ids = null;
        for (final HasCurrencyIds leg : this.legs()) {
            ids = ids == null ? leg.currencyIds() : ids.union(leg.currencyIds());
        }
        return ids;
    }

}
