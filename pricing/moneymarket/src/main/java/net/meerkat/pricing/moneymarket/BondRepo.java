package net.meerkat.pricing.moneymarket;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.instrument.bond.BondProvider;
import net.meerkat.instrument.moneymarket.Repo;

/**
 *
 * @author Ollie
 */
public interface BondRepo<C extends CurrencyId> extends Repo<C> {

    @Nonnull
    default Bond collateral(final BondProvider bondProvider) {
        return bondProvider.require(this.collateralId());
    }

}
