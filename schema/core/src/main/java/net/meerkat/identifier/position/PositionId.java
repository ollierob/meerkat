package net.meerkat.identifier.position;

import net.meerkat.identifier.SecurityInMarketId;
import net.meerkat.identifier.portfolio.PortfolioId;

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
