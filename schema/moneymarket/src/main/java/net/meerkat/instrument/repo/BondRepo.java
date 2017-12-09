package net.meerkat.instrument.repo;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.instrument.bond.BondSnapshot;
import net.meerkat.instrument.bond.exception.UnknownBondException;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.instrument.repo.repurchase.RepoRepurchase;
import net.meerkat.issuer.IssuerId;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public class BondRepo<C extends CurrencyId> extends AbstractRepo<C> {

    public BondRepo(String name, InstrumentIds identifiers, IssuerId issuerId, CashPayment<C> purchase, RepoRepurchase repurchase, InstrumentId collateralId) {
        super(name, identifiers, issuerId, purchase, repurchase, collateralId);
    }

    @Nonnull
    public Bond collateral(final BondSnapshot bondProvider) throws UnknownBondException {
        return bondProvider.require(this.collateralId());
    }

    @Override
    public <R> R handleWith(final Repo.Handler<R> handler) {
        return handler.handle(this);
    }

}
