package net.meerkat.risk.portfolio;

import net.coljate.map.Map;
import net.coljate.set.Set;
import net.meerkat.risk.position.Position;
import net.meerkat.risk.position.PositionId;
import net.meerkat.risk.position.PositionProvider;

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
    default Map<PortfolioId, ? extends Book> childPortfolios(final PortfolioSnapshot portfolios) {
        return Map.of();
    }

    @Override
    @Deprecated
    default Map<PositionId, ? extends Position> childPositions(final PortfolioSnapshot portfolios, final PositionProvider positionProvider) {
        return Map.of();
    }

    static Book of(final PortfolioId portfolioId, final Set<PositionId> positionIds) {
        return new MinimalBook(portfolioId, positionIds);
    }

}
