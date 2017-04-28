package net.meerkat.instrument.interest.future.stir;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.derivative.forward.AbstractFuture;
import net.meerkat.instrument.derivative.forward.FutureDeliveryDates;
import net.meerkat.instrument.interest.future.InterestRateFuture;

/**
 *
 * @author ollie
 */
public class ShortTermInterestRateFuture<C extends CurrencyId>
        extends AbstractFuture<InterestRateFutureContract<C>>
        implements InterestRateFuture<C, InterestRateFutureContract<C>> {

    @XmlElementRef(name = "deliveryDates")
    private FutureDeliveryDates deliveryDates;

    @XmlElementRef(name = "contract")
    private InterestRateFutureContract<C> contract;

    @Override
    public C currencyId() {
        return this.underlying().currencyId();
    }

    @Override
    public FutureDeliveryDates dates() {
        return deliveryDates;
    }

    @Override
    public InterestRateFutureContract<C> underlying() {
        return contract;
    }

}
