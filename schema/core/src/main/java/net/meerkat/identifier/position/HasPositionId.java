package net.meerkat.identifier.position;

import javax.annotation.Nonnull;

import net.meerkat.identifier.HasSecurityInMarketId;
import net.meerkat.identifier.SecurityInMarketId;
import net.meerkat.identifier.portfolio.HasPortfolioId;
import net.meerkat.identifier.portfolio.PortfolioId;

/**
 *
 * @author Ollie
 */
public interface HasPositionId extends HasSecurityInMarketId, HasPortfolioId {

    @Nonnull
    PositionId positionId();

    @Override
    public default PortfolioId portfolioId() {
        return this.positionId().portfolioId();
    }

    @Override
    default SecurityInMarketId securityInMarketId() {
        return this.positionId().securityInMarketId();
    }

}
