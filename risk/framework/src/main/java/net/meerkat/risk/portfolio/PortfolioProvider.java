package net.meerkat.risk.portfolio;

import net.ollie.goat.data.Provider;

/**
 *
 * @author Ollie
 */
public interface PortfolioProvider extends Provider<PortfolioId, Portfolio> {

    @Override
    default Portfolio require(final PortfolioId key) throws UnknownPortfolioException {
        return this.require(key, UnknownPortfolioException::new);
    }

}
