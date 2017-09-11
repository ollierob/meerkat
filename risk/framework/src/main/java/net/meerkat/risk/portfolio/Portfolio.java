package net.meerkat.risk.portfolio;

import javax.annotation.Nonnull;

import net.coljate.map.Map;
import net.coljate.map.MutableMap;
import net.coljate.set.MutableSet;
import net.coljate.set.Set;
import net.meerkat.position.Position;
import net.meerkat.position.PositionId;
import net.meerkat.position.PositionProvider;
import net.meerkat.position.UnknownPositionException;
import net.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
public interface Portfolio extends HasName, HasPortfolioId {

    @Nonnull
    Set<PortfolioId> childPortfolioIds();

    default Map<PortfolioId, ? extends Portfolio> childPortfolios(final PortfolioProvider portfolios) throws UnknownPortfolioException {
        return Map.mapValues(this.childPortfolioIds(), portfolios::require);
    }

    default Map<PortfolioId, ? extends Portfolio> allPortfolios(final PortfolioProvider portfolios) throws UnknownPortfolioException {
        return Map.<PortfolioId, Portfolio>covariantValues(this.childPortfolios(portfolios)).union(this.portfolioId(), this);
    }

    @Nonnull
    Set<PositionId> ownPositionIds();

    @Nonnull
    default Set<PositionId> allPositionIds(final PortfolioProvider portfolios) {
        final MutableSet<PositionId> positions = MutableSet.copyIntoHashSet(this.ownPositionIds());
        for (final PortfolioId child : this.childPortfolioIds()) {
            final Portfolio portfolio = portfolios.require(child);
            positions.addAll(portfolio.allPositionIds(portfolios));
        }
        return positions;
    }

    default Map<PositionId, ? extends Position> ownPositions(final PositionProvider positions) throws UnknownPositionException {
        return Map.mapValues(this.ownPositionIds(), positions::require);
    }

    default Map<PositionId, ? extends Position> childPositions(final PortfolioProvider portfolioProvider, final PositionProvider positionProvider) {
        final MutableMap<PositionId, Position> positions = Map.create(100);
        for (final Portfolio child : this.childPortfolios(portfolioProvider).values()) {
            positions.putAll(child.allPositions(portfolioProvider, positionProvider));
        }
        return positions;
    }

    default Map<PositionId, ? extends Position> allPositions(final PortfolioProvider portfolioProvider, final PositionProvider positionProvider) {
        return Map.<PositionId, Position>covariantValues(this.ownPositions(positionProvider)).union(this.childPositions(portfolioProvider, positionProvider));
    }

}
