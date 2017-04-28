package net.meerkat.instrument.interest.future.stir;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.derivative.forward.AbstractFuture;
import net.meerkat.instrument.interest.future.InterestRateFuture;
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

    @XmlElementRef(name = "deliveryDates")
    private Interim deliveryDates;

    @XmlElementRef(name = "contract")
    private ShortTermInterestRateFutureContract<C> contract;

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
