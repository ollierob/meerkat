package net.meerkat.instrument.interest.future.stir;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.derivative.forward.AbstractFuture;
import net.meerkat.instrument.interest.future.InterestRateFuture;
import net.meerkat.issue.Issue;
import net.meerkat.money.Money;
import net.ollie.goat.temporal.date.interim.Interim;

/**
 *
 * @author ollie
 */
public class ShortTermInterestRateFuture<C extends CurrencyId>
        extends AbstractFuture<ShortTermInterestRateFutureContract<C>>
        implements InterestRateFuture<C, ShortTermInterestRateFutureContract<C>> {

    private static final long serialVersionUID = 1L;

    private final Interim deliveryDates;
    private final ShortTermInterestRateFutureContract<C> contract;

    public ShortTermInterestRateFuture(final String name, InstrumentIds identifiers, Issue issue, Interim deliveryDates, ShortTermInterestRateFutureContract<C> contract) {
        super(name, identifiers, issue);
        this.deliveryDates = deliveryDates;
        this.contract = contract;
    }

    @Override
    public Money<C> notional() {
        return this.underlying().notional();
    }

    @Override
    public Interim deliveryDates() {
        return deliveryDates;
    }

    @Override
    public ShortTermInterestRateFutureContract<C> underlying() {
        return contract;
    }

}
