package net.ollie.meerkat.security.bond.future;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Period;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.security.bond.Bond;
import net.ollie.meerkat.security.bond.BondDerivative;
import net.ollie.meerkat.security.derivative.forward.AbstractFuture;
import net.ollie.meerkat.security.derivative.forward.FutureDelivery;

/**
 *
 * @author ollie
 */
public class BondFuture
        extends AbstractFuture<BondBasket>
        implements BondDerivative<BondBasket> {

    @XmlElementRef(name = "basket")
    private BondBasket basket;

    @XmlElement(name = "notional")
    private Money notional;

    @XmlAttribute(name = "rate")
    private Percentage rate;

    @XmlAttribute(name = "maturity")
    private Period minimumMaturity;

    @XmlElementRef(name = "delivery")
    private FutureDelivery<Month> deliveryMonths;

    @Deprecated
    BondFuture() {
    }

    @Override
    public BondBasket underlying() {
        return basket;
    }

    @Override
    public FutureDelivery<Month> deliveryDates() {
        return deliveryMonths;
    }

    @Nonnull
    public <B extends Bond> Optional<B> cheapestToDeliver(final Map<B, BigDecimal> bondPrices) {
        return bondPrices.entrySet()
                .stream()
                .filter(e -> basket.contains(e.getKey()))
                .min(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey);
    }

    @Override
    public <R> R handleWith(final BondDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
