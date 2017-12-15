package net.meerkat.risk.position;

import net.meerkat.risk.position.exception.PositionException;
import net.meerkat.risk.position.exception.UnavailablePositionException;
import net.ollie.goat.data.CompositeProvider;

import javax.annotation.Nonnull;

public interface PositionProvider<T, P extends Position> extends CompositeProvider<T, PositionId, P, PositionSnapshot<P>> {

    @Nonnull
    @Override
    default PositionSnapshot<P> require(final T key) throws UnavailablePositionException {
        return this.require(key, UnavailablePositionException::new);
    }

    @Nonnull
    @Override
    default P require(final T t, final PositionId positionId) throws PositionException {
        return CompositeProvider.super.require(t, positionId);
    }

}
