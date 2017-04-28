package net.meerkat.instrument.interest.future.bond;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.derivative.forward.AbstractFuture;
import net.meerkat.instrument.interest.future.InterestRateFuture;
import net.ollie.goat.temporal.date.interim.Interim;
import net.meerkat.instrument.interest.future.InterestRateFutureContract;

/**
 *
 * @author ollie
 */
public class BondFuture<C extends CurrencyId>
        extends AbstractFuture<InterestRateFutureContract<C>>
        implements InterestRateFuture<C, InterestRateFutureContract<C>> {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "currencyId")
    private C currencyId;

    @XmlElementRef(name = "basket")
    private BondFutureContract<C> basket;

    @XmlElementRef(name = "deliveryDates")
    private Interim deliveryDates;

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
