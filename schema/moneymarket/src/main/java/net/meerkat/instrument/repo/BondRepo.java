package net.meerkat.instrument.repo;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.instrument.bond.BondProvider;
import net.meerkat.instrument.bond.exception.UnknownBondException;

/**
 *
 * @author Ollie
 */
public interface BondRepo<C extends CurrencyId> extends Repo<C> {

    @Nonnull
    default Bond collateral(final BondProvider bondProvider) throws UnknownBondException {
        return bondProvider.require(this.collateralId());
    }

}
