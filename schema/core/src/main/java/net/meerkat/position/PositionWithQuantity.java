package net.meerkat.position;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface PositionWithQuantity extends Position {

    @Nonnull
    Number quantity();

}
