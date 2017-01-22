package net.meerkat.identifier.portfolio;

/**
 *
 * @author Ollie
 */
public interface PortfolioId extends HasPortfolioId {

    @Override
    default PortfolioId portfolioId() {
        return this;
    }

}
