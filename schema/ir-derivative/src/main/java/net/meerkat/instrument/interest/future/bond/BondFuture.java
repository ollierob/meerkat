package net.meerkat.instrument.interest.future.bond;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.derivative.forward.AbstractFuture;
import net.meerkat.instrument.derivative.forward.FutureDelivery;
import net.meerkat.instrument.interest.future.InterestRateFuture;
import net.meerkat.instrument.interest.future.InterestRateFutureContract;
import net.meerkat.instrument.interest.future.stir.ShortTermInterestRateFuture;
import net.meerkat.issuer.IssuerId;

/**
 * Long-term interest rate future.
 *
 * @author Ollie
 * @see ShortTermInterestRateFuture
 */
public class BondFuture<C extends CurrencyId>
        extends AbstractFuture<InterestRateFutureContract<C>>
        implements InterestRateFuture<C, InterestRateFutureContract<C>> {

    private final C currencyId;
    private final BondFutureContract<C> basket;
    private final FutureDelivery delivery;

    public BondFuture(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuerId,
            final C currencyId,
            final BondFutureContract<C> basket,
            final FutureDelivery delivery) {
        super(name, identifiers, issuerId);
        this.currencyId = currencyId;
        this.basket = basket;
        this.delivery = delivery;
    }

    @Override
    public FutureDelivery delivery() {
        return delivery;
    }

    @Override
    public BondFutureContract<C> underlying() {
        return basket;
    }

    @Override
    public C currencyId() {
        return currencyId;
    }

}
