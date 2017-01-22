package net.meerkat.portfolio;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import net.meerkat.numeric.quantity.Quantity;
import net.meerkat.identifier.position.PositionId;
import net.ollie.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
public interface Portfolio extends HasName {

    @Nonnull
    Set<? extends Portfolio> children();

    @Nonnull
    Map<PositionId, ? extends Quantity> ownPositions();

    @Nonnull
    default Map<PositionId, ? extends Quantity> allPositions() {
        final Map<PositionId, Quantity> positions = new HashMap<>(this.ownPositions());
        this.children().forEach(portfolio -> positions.putAll(portfolio.allPositions()));
        return positions;
    }

}
