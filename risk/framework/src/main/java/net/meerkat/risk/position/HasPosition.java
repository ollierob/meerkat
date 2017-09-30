package net.meerkat.risk.position;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface HasPosition extends HasPositionId {

    @Nonnull
    Position position();

    @Override
    default PositionId positionId() {
        return this.position().positionId();
    }

}
