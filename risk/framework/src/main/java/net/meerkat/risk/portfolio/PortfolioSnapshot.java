package net.meerkat.risk.portfolio;

import net.meerkat.risk.portfolio.exception.UnknownPortfolioException;
import net.ollie.goat.data.Provider;

/**
 * @author Ollie
 */
public interface PortfolioSnapshot extends Provider<PortfolioId, Portfolio> {

    @Override
    default Portfolio require(final PortfolioId portfolioId) throws UnknownPortfolioException {
        return this.require(portfolioId, UnknownPortfolioException::new);
    }

}
