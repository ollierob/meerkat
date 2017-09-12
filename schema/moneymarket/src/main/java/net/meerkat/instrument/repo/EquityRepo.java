package net.meerkat.instrument.repo;

import javax.annotation.Nonnull;

import net.meerkat.instrument.equity.Equity;
import net.meerkat.instrument.equity.EquityProvider;
import net.meerkat.instrument.equity.UnknownEquityException;

/**
 *
 * @author Ollie
 */
public interface EquityRepo extends Repo {

    @Nonnull
    default Equity collateral(final EquityProvider equityProvider) throws UnknownEquityException {
        return equityProvider.require(this.collateralId());
    }

}
