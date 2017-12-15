package net.meerkat.risk.position;

import net.meerkat.risk.position.exception.UnknownPositionException;
import net.ollie.goat.data.Provider;

/**
 * @author Ollie
 */
public interface PositionSnapshot<P extends Position> extends Provider<PositionId, P> {

    @Override
    default P require(final PositionId key) throws UnknownPositionException {
        return this.require(key, UnknownPositionException::new);
    }

}
