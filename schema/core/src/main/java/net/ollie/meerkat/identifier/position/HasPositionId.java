package net.ollie.meerkat.identifier.position;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.HasSecurityInMarketId;
import net.ollie.meerkat.identifier.SecurityInMarketId;
import net.ollie.meerkat.identifier.portfolio.HasPortfolioId;
import net.ollie.meerkat.identifier.portfolio.PortfolioId;

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
