package net.meerkat.identifier.currency;

import javax.annotation.Nonnull;

import net.coljate.set.Set;

/**
 *
 * @author ollie
 */
public class CurrencyIdPair<B extends CurrencyId, C extends CurrencyId>
        implements CurrencyIds {

    private final B baseId;
    private final C counterId;

    public CurrencyIdPair(final B baseId, final C counterId) {
        this.baseId = baseId;
        this.counterId = counterId;
    }

    @Nonnull
    public B baseCurrencyId() {
        return baseId;
    }

    @Nonnull
    public C counterCurrencyId() {
        return counterId;
    }

    @Override
    public int count() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Set<? extends CurrencyId> values() {
        return Set.of(this.baseCurrencyId(), this.counterCurrencyId());
    }

    @Override
    @Deprecated
    public CurrencyIdPair<B, C> currencyIds() {
        return this;
    }

}
