package net.meerkat.identifier.position;

import javax.annotation.Nonnull;

import net.meerkat.identifier.portfolio.HasPortfolioId;
import net.meerkat.identifier.portfolio.PortfolioId;
import net.meerkat.identifier.HasInstrumentInMarketId;
import net.meerkat.identifier.InstrumentInMarketId;

/**
 *
 * @author Ollie
 */
public interface HasPositionId extends HasInstrumentInMarketId, HasPortfolioId {

    @Nonnull
    PositionId positionId();

    @Override
    public default PortfolioId portfolioId() {
        return this.positionId().portfolioId();
    }

    @Override
    default InstrumentInMarketId instrumentInMarketId() {
        return this.positionId().instrumentInMarketId();
    }

}
