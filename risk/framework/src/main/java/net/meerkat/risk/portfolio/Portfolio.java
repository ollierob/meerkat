package net.meerkat.risk.portfolio;

import net.coljate.map.Map;
import net.coljate.map.MutableMap;
import net.coljate.set.MutableSet;
import net.coljate.set.Set;
import net.meerkat.risk.portfolio.exception.UnknownPortfolioException;
import net.meerkat.risk.position.Position;
import net.meerkat.risk.position.PositionId;
import net.meerkat.risk.position.PositionSnapshot;
import net.meerkat.risk.position.exception.UnknownPositionException;

import javax.annotation.Nonnull;

/**
 * @author Ollie
 */
public interface Portfolio extends HasPortfolioId {

    @Nonnull
    Set<PortfolioId> childPortfolioIds();

    default Map<PortfolioId, ? extends Portfolio> childPortfolios(final PortfolioSnapshot portfolios) throws UnknownPortfolioException {
        return Map.mapValues(this.childPortfolioIds(), portfolios::require);
    }

    default Map<PortfolioId, ? extends Portfolio> allPortfolios(final PortfolioSnapshot portfolios) throws UnknownPortfolioException {
        return Map.<PortfolioId, Portfolio>covariantValues(this.childPortfolios(portfolios)).union(this.portfolioId(), this);
    }

    @Nonnull
    Set<PositionId> ownPositionIds();

    @Nonnull
    default Set<PositionId> allPositionIds(final PortfolioSnapshot portfolios) {
        final MutableSet<PositionId> positions = MutableSet.copyIntoHashSet(this.ownPositionIds());
        for (final PortfolioId child : this.childPortfolioIds()) {
            final Portfolio portfolio = portfolios.require(child);
            positions.addAll(portfolio.allPositionIds(portfolios));
        }
        return positions;
    }

    default Map<PositionId, ? extends Position> ownPositions(final PositionSnapshot positions) throws UnknownPositionException {
        return Map.mapValues(this.ownPositionIds(), positions::require);
    }

    default Map<PositionId, ? extends Position> childPositions(final PortfolioSnapshot portfolios, final PositionSnapshot positionSnapshot) {
        final MutableMap<PositionId, Position> positions = Map.create(100);
        for (final Portfolio child : this.childPortfolios(portfolios).values()) {
            positions.putAll(child.allPositions(portfolios, positionSnapshot));
        }
        return positions;
    }

    default Map<PositionId, ? extends Position> allPositions(final PortfolioSnapshot portfolioSnapshot, final PositionSnapshot positionSnapshot) {
        return Map.<PositionId, Position>covariantValues(this.ownPositions(positionSnapshot)).union(this.childPositions(portfolioSnapshot, positionSnapshot));
    }

}
