package net.ollie.meerkat.identifier.position;

import net.ollie.meerkat.identifier.portfolio.PortfolioId;
import net.ollie.meerkat.identifier.security.SecurityId;

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
    SecurityId securityId();

}
