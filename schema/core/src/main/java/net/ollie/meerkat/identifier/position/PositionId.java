package net.ollie.meerkat.identifier.position;

import net.ollie.meerkat.identifier.SecurityInMarketId;
import net.ollie.meerkat.identifier.portfolio.PortfolioId;

/**
 *
 * @author Ollie
 */
public interface PositionId extends HasPositionId {

    @Override
    default PositionId positionId() {
        return this;
    }

    @Override
    PortfolioId portfolioId();

    @Override
    SecurityInMarketId securityInMarketId();

}
