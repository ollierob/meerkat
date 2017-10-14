package net.meerkat.risk.position;

import javax.annotation.Nonnull;

import net.meerkat.identifier.HasInstrumentInMarketId;
import net.meerkat.identifier.InstrumentInMarketId;
import net.meerkat.identifier.market.MarketId;
import net.meerkat.identifier.portfolio.HasPortfolioId;
import net.meerkat.identifier.portfolio.PortfolioId;

/**
 *
 * @author Ollie
 */
public interface HasPositionId extends HasInstrumentInMarketId, HasPortfolioId {

    @Nonnull
    PositionId positionId();

    @Override
    default PortfolioId portfolioId() {
        return this.positionId().portfolioId();
    }

    @Override
    default MarketId marketId() {
        return this.positionId().marketId();
    }

    @Override
    default InstrumentInMarketId instrumentInMarketId() {
        return this.positionId().instrumentInMarketId();
    }

}
