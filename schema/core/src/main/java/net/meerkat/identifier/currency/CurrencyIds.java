package net.meerkat.identifier.currency;

import javax.annotation.CheckReturnValue;

import net.coljate.collection.Collection;
import net.coljate.set.Set;
import net.meerkat.identifier.instrument.InstrumentIds;

/**
 *
 * @author ollie
 */
public interface CurrencyIds extends InstrumentIds, HasCurrencyIds {

    public static CurrencyIds of(final InstrumentIds ids) {
        return ids instanceof CurrencyIds
                ? (CurrencyIds) ids
                : of(ids.thatAre(CurrencyId.class));
    }

    public static CurrencyIds of(final CurrencyId id) {
        return new CurrencyIdSet(Set.of(id));
    }

    public static CurrencyIds of(final Collection<CurrencyId> ids) {
        return new CurrencyIdSet(Set.copyIntoHashSet(ids));
    }

    public static CurrencyIds of(final CurrencyId... ids) {
        return new CurrencyIdSet(Set.of(ids));
    }

    public static CurrencyIds of(final CurrencyIds... currencyIds) {
        Set<CurrencyId> set = Set.of();
        for (final CurrencyIds ids : currencyIds) {
            set = set.union(ids.values());
        }
        return new CurrencyIdSet(set);
    }

    Set<? extends CurrencyId> values();

    @Override
    @Deprecated
    default CurrencyIds currencyIds() {
        return this;
    }

    @CheckReturnValue
    default CurrencyIds union(final CurrencyIds that) {
        final Set<CurrencyId> ids = Set.<CurrencyId>of().union(this.values()).union(that.values());
        return new CurrencyIdSet(ids);
    }

}
