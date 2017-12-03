package net.meerkat.risk.portfolio;

import net.coljate.set.Set;
import net.meerkat.risk.position.PositionId;

import javax.annotation.Nonnull;

public class MinimalBook implements Book {

    private final PortfolioId portfolioId;
    private final Set<PositionId> positionIds;

    public MinimalBook(final PortfolioId portfolioId, final Set<PositionId> positionIds) {
        this.portfolioId = portfolioId;
        this.positionIds = positionIds;
    }

    @Nonnull
    @Override
    public PortfolioId portfolioId() {
        return portfolioId;
    }

    @Nonnull
    @Override
    public Set<PositionId> ownPositionIds() {
        return positionIds;
    }

}
