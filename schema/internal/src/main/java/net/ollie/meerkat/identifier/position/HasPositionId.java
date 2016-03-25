package net.ollie.meerkat.identifier.position;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.security.SecurityId;
import net.ollie.meerkat.identifier.security.HasSecurityId;
import net.ollie.meerkat.identifier.portfolio.PortfolioId;
import net.ollie.meerkat.identifier.portfolio.HasPortfolioId;

/**
 *
 * @author Ollie
 */
public interface HasPositionId extends HasSecurityId, HasPortfolioId {

    @Nonnull
    PositionId positionId();

    @Override
    default SecurityId securityId() {
        return this.positionId().securityId();
    }

    @Override
    public default PortfolioId portfolioId() {
        return this.positionId().portfolioId();
    }

}
