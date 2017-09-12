package net.meerkat.instrument.repo;

import javax.annotation.Nonnull;

import net.meerkat.instrument.bond.Bond;
import net.meerkat.instrument.bond.BondProvider;
import net.meerkat.instrument.bond.UnknownBondException;

/**
 *
 * @author Ollie
 */
public interface BondRepo extends Repo {

    @Nonnull
    default Bond collateral(final BondProvider bondProvider) throws UnknownBondException {
        return bondProvider.require(this.collateralId());
    }

}
