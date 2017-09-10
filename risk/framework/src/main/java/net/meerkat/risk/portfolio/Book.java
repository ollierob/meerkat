package net.meerkat.risk.portfolio;

import net.coljate.map.Map;
import net.coljate.set.Set;
import net.meerkat.position.PositionId;
import net.meerkat.position.Position;
import net.meerkat.position.PositionProvider;

/**
 * Portfolio of positions with no children.
 *
 * @author Ollie
 */
public interface Book extends Portfolio {

    @Override
    @Deprecated
    default Set<PortfolioId> childPortfolioIds() {
        return Set.of();
    }

    @Override
    @Deprecated
    default Map<PortfolioId, ? extends Book> childPortfolios(final PortfolioProvider portfolios) {
        return Map.of();
    }

    @Override
    @Deprecated
    default Map<PositionId, ? extends Position> childPositions(PortfolioProvider portfolioProvider, PositionProvider positionProvider) {
        return Map.of();
    }

}
