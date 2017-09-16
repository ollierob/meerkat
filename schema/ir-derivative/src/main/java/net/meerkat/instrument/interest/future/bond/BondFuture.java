package net.meerkat.instrument.interest.future.bond;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.derivative.forward.AbstractFuture;
import net.meerkat.instrument.interest.future.InterestRateFuture;
import net.meerkat.instrument.interest.future.InterestRateFutureContract;
import net.meerkat.issue.Issue;
import net.ollie.goat.temporal.date.interim.Interim;

/**
 *
 * @author ollie
 */
public class BondFuture<C extends CurrencyId>
        extends AbstractFuture<InterestRateFutureContract<C>>
        implements InterestRateFuture<C, InterestRateFutureContract<C>> {

    private final C currencyId;
    private final BondFutureContract<C> basket;
    private final Interim deliveryDates;

    public BondFuture(final String name, InstrumentIds identifiers, Issue issue, final C currencyId, BondFutureContract<C> basket, Interim deliveryDates) {
        super(name, identifiers, issue);
        this.currencyId = currencyId;
        this.basket = basket;
        this.deliveryDates = deliveryDates;
    }

    @Override
    public Interim deliveryDates() {
        return deliveryDates;
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
