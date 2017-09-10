package net.meerkat.calculate.sensitivity;

import javax.annotation.Nonnull;

import net.coljate.list.List;
import net.coljate.set.Set;
import net.meerkat.position.Position;

/**
 * Sensitivities for a position.
 *
 * @author Ollie
 */
public interface PositionSensitivities extends Sensitivities {

    @Nonnull
    Set<? extends Position> positions();

    default PositionSensitivities plus(final PositionSensitivities that) {
        return new LazyPositionSensitivities(List.of(this, that));
    }

}
