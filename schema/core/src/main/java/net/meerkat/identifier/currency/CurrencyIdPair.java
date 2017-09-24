package net.meerkat.identifier.currency;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.baseId);
        hash = 73 * hash + Objects.hashCode(this.counterId);
        return hash;
    }

    @Override
    public boolean equals(final Object that) {
        return that instanceof CurrencyIdPair
                && this.equals((CurrencyIdPair<?, ?>) that);
    }

    protected boolean equals(@Nonnull final CurrencyIdPair<?, ?> that) {
        return Objects.equals(this.baseId, that.baseId)
                && Objects.equals(this.counterId, that.counterId);
    }

    @Override
    public String toString() {
        return baseId + "/" + counterId;
    }

}
