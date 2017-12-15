package net.meerkat.instrument.interest.future.stir;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.derivative.forward.AbstractFuture;
import net.meerkat.instrument.derivative.forward.FutureDelivery;
import net.meerkat.instrument.interest.future.InterestRateFuture;
import net.meerkat.instrument.interest.future.bond.BondFuture;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;

/**
 * Short-term interest rate future.
 *
 * @author Ollie
 * @see BondFuture
 */
public class ShortTermInterestRateFuture<C extends CurrencyId>
        extends AbstractFuture<ShortTermInterestRateFutureContract<C>>
        implements InterestRateFuture<C, ShortTermInterestRateFutureContract<C>> {

    private final FutureDelivery delivery;
    private final ShortTermInterestRateFutureContract<C> contract;

    public ShortTermInterestRateFuture(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuerId,
            final FutureDelivery delivery,
            final ShortTermInterestRateFutureContract<C> contract) {
        super(name, identifiers, issuerId);
        this.delivery = delivery;
        this.contract = contract;
    }

    @Override
    public Money<C> notional() {
        return this.underlying().notional();
    }

    @Override
    public FutureDelivery delivery() {
        return delivery;
    }

    @Override
    public ShortTermInterestRateFutureContract<C> underlying() {
        return contract;
    }

}
