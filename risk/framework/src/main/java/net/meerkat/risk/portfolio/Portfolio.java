package net.meerkat.risk.portfolio;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import net.meerkat.identifier.position.PositionId;
import net.meerkat.risk.position.Position;
import net.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
public interface Portfolio extends HasName, HasPortfolioId {

    @Nonnull
    Set<? extends Portfolio> children();

    default Map<PortfolioId, ? extends Portfolio> portfolioIds() {
        final Map<PortfolioId, Portfolio> byId = new HashMap<>();
        byId.put(this.portfolioId(), this);
        this.children().forEach(child -> byId.putAll(child.portfolioIds()));
        return byId;
    }

    @Nonnull
    Map<PositionId, ? extends Position> ownPositions();

    @Nonnull
    default Map<PositionId, ? extends Position> allPositions() {
        final Map<PositionId, Position> positions = new HashMap<>(this.ownPositions());
        this.children().forEach(portfolio -> positions.putAll(portfolio.allPositions()));
        return positions;
    }

}
