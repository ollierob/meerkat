package net.meerkat.risk;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import net.meerkat.identifier.position.PositionId;
import net.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
public interface Portfolio extends HasName {

    @Nonnull
    Set<? extends Portfolio> children();

    @Nonnull
    Map<PositionId, ? extends Position> ownPositions();

    @Nonnull
    default Map<PositionId, ? extends Position> allPositions() {
        final Map<PositionId, Position> positions = new HashMap<>(this.ownPositions());
        this.children().forEach(portfolio -> positions.putAll(portfolio.allPositions()));
        return positions;
    }

}
