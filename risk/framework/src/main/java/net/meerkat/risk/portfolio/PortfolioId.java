package net.meerkat.risk.portfolio;

/**
 *
 * @author Ollie
 */
public interface PortfolioId extends HasPortfolioId {

    @Override
    @Deprecated
    default PortfolioId portfolioId() {
        return this;
    }

}
