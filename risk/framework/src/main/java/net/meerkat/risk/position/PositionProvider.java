package net.meerkat.risk.position;

import net.ollie.goat.data.Provider;

/**
 *
 * @author Ollie
 */
public interface PositionProvider extends Provider<PositionId, Position> {

    @Override
    default Position require(final PositionId key) throws UnknownPositionException {
        return this.require(key, UnknownPositionException::new);
    }

}
