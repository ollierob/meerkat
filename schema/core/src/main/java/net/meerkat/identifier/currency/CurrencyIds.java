package net.meerkat.identifier.currency;

import javax.annotation.CheckReturnValue;

import net.coljate.collection.Collection;
import net.coljate.set.Set;
import net.meerkat.identifier.instrument.InstrumentIds;

/**
 *
 * @author ollie
 */
public class CurrencyIds
        extends InstrumentIds
        implements HasCurrencyIds {

    public static CurrencyIds of(final InstrumentIds ids) {
        return ids instanceof CurrencyIds
                ? (CurrencyIds) ids
                : of(ids.thatAre(CurrencyId.class));
    }

    public static CurrencyIds of(final CurrencyId id) {
        return new CurrencyIds(Set.of(id));
    }

    public static CurrencyIds of(final Collection<CurrencyId> ids) {
        return new CurrencyIds(Set.copyIntoHashSet(ids));
    }

    public static CurrencyIds of(final CurrencyId... ids) {
        return new CurrencyIds(Set.of(ids));
    }

    public static CurrencyIds of(final CurrencyIds... currencyIds) {
        Set<CurrencyId> set = Set.of();
        for (final CurrencyIds ids : currencyIds) {
            set = set.union(ids.values());
        }
        return new CurrencyIds(set);
    }

    public CurrencyIds(final Set<? extends CurrencyId> ids) {
        super(ids);
    }

    public Set<? extends CurrencyId> values() {
        return this.thatAre(CurrencyId.class);
    }

    @Override
    public CurrencyIds currencyIds() {
        return this;
    }

    @CheckReturnValue
    public CurrencyIds union(final CurrencyIds that) {
        final Set<CurrencyId> ids = Set.<CurrencyId>of().union(this.values()).union(that.values());
        return new CurrencyIds(ids);
    }

}
