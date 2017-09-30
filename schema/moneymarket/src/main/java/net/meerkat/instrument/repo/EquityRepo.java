package net.meerkat.instrument.repo;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.instrument.equity.Equity;
import net.meerkat.instrument.equity.EquityProvider;
import net.meerkat.instrument.equity.exception.UnknownEquityException;
import net.meerkat.instrument.repo.repurchase.RepoRepurchase;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author Ollie
 */
public class EquityRepo extends AbstractRepo<CurrencyId> {

    public EquityRepo(String name, InstrumentIds identifiers, IssuerId issuerId, CashPayment<CurrencyId> purchase, RepoRepurchase repurchase, InstrumentId collateralId) {
        super(name, identifiers, issuerId, purchase, repurchase, collateralId);
    }

    @Nonnull
    public Equity collateral(final EquityProvider equityProvider) throws UnknownEquityException {
        return equityProvider.require(this.collateralId());
    }

    @Override
    public <R> R handleWith(final Handler<R> handler) {
        return handler.handle(this);
    }

}
