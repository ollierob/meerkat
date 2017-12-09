package net.meerkat.instrument.repo;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.InstrumentProvider;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.instrument.exception.UnknownInstrumentException;
import net.meerkat.instrument.repo.repurchase.RepoRepurchase;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.interest.fixed.FixedInterestRate;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 *
 * @author Ollie
 */
public abstract class AbstractRepo<C extends CurrencyId> extends IssuedSecurity implements Repo<C> {

    private final CashPayment<C> purchase;
    private final RepoRepurchase repurchase;
    private final InstrumentId collateralId;

    public AbstractRepo(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuerId,
            final CashPayment<C> purchase,
            final RepoRepurchase repurchase,
            final InstrumentId collateralId) {
        super(name, identifiers, issuerId);
        this.purchase = purchase;
        this.repurchase = repurchase;
        this.collateralId = collateralId;
    }

    @Override
    public CashPayment<C> purchase() {
        return purchase;
    }

    @Override
    public RepoRepurchase repurchase() {
        return repurchase;
    }

    @Override
    public InstrumentId collateralId() {
        return collateralId;
    }

    @Nonnull
    @Override
    public Optional<? extends FixedInterestRate> impliedRate() {
        return Optional.empty();
    }

    public <I extends Instrument> I collateral(final InstrumentProvider<? extends I> instrumentProvider) throws UnknownInstrumentException {
        return instrumentProvider.require(collateralId);
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("purchase", purchase)
                .put("repurchase", repurchase)
                .put("collateral", collateralId);
    }

}
