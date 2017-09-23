package net.meerkat.identifier.currency;

import net.coljate.set.Set;
import net.meerkat.identifier.instrument.InstrumentIdSet;

/**
 *
 * @author ollie
 */
public class CurrencyIdSet
        extends InstrumentIdSet
        implements CurrencyIds {

    public CurrencyIdSet(final Set<? extends CurrencyId> ids) {
        super(ids);
    }

    @Override
    public Set<? extends CurrencyId> values() {
        return this.thatAre(CurrencyId.class);
    }

}
