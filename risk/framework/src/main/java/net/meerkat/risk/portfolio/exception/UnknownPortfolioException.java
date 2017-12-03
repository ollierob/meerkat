package net.meerkat.risk.portfolio.exception;

import net.meerkat.risk.portfolio.PortfolioId;

/**
 * @author Ollie
 */
public class UnknownPortfolioException extends PortfolioException {

    private static final long serialVersionUID = 1L;

    public UnknownPortfolioException(final PortfolioId id) {
        super("Unknown portfolio for " + id);
    }

}
