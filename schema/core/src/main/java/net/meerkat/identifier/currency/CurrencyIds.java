package net.meerkat.identifier.currency;

import java.util.Collections;
import java.util.Set;

import net.meerkat.identifier.HasIds;
import net.ollie.goat.collection.Sets;

/**
 *
 * @author ollie
 */
public class CurrencyIds
        extends HasIds<CurrencyId>
        implements HasCurrencyIds {

    private static final long serialVersionUID = 1L;

    public static CurrencyIds of(final CurrencyId id) {
        return new CurrencyIds(Collections.singleton(id));
    }

    public static CurrencyIds of(final CurrencyId... ids) {
        return new CurrencyIds(Sets.asSet(ids));
    }

    @Deprecated
    CurrencyIds() {
    }

    public CurrencyIds(final Set<CurrencyId> ids) {
        super(ids);
    }

    @Override
    public CurrencyIds currencyIds() {
        return this;
    }

}
