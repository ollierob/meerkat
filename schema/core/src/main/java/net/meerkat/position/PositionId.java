package net.meerkat.position;

import net.meerkat.identifier.portfolio.PortfolioId;
import net.meerkat.identifier.InstrumentInMarketId;

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
