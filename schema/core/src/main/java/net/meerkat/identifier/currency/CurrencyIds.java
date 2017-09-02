package net.meerkat.identifier.currency;

import net.coljate.collection.Collection;
import net.coljate.set.Set;
import net.meerkat.identifier.HasIds;

/**
 *
 * @author ollie
 */
public class CurrencyIds
        extends HasIds<CurrencyId>
        implements HasCurrencyIds {

    public static CurrencyIds of(final CurrencyId id) {
        return new CurrencyIds(Set.of(id));
    }

    public static CurrencyIds of(final Collection<CurrencyId> ids) {
        return new CurrencyIds(Set.copyIntoHashSet(ids));
    }

    public static CurrencyIds of(final CurrencyId... ids) {
        return new CurrencyIds(Set.of(ids));
    }

    public CurrencyIds(final Set<CurrencyId> ids) {
        super(ids);
    }

    @Override
    public CurrencyIds currencyIds() {
        return this;
    }

}
