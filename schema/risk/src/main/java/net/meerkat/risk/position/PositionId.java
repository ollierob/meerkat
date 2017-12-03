package net.meerkat.risk.position;

import net.meerkat.identifier.InstrumentInMarketId;
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
    InstrumentInMarketId instrumentInMarketId();

}
