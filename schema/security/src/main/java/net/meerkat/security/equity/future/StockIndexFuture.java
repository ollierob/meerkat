package net.meerkat.security.equity.future;

import javax.xml.bind.annotation.XmlElement;

import net.meerkat.security.derivative.forward.AbstractFuture;
import net.meerkat.security.equity.EquityDerivative;
import net.meerkat.security.equity.StockIndex;
import net.meerkat.security.derivative.forward.FutureDeliveryDates;

/**
 *
 * @author Ollie
 */
public class StockIndexFuture
        extends AbstractFuture<StockIndex>
        implements EquityDerivative<StockIndex> {

    @XmlElement(name = "underlying")
    private StockIndex underlying;

    @Deprecated
    StockIndexFuture() {
    }

    @Override
    public StockIndex underlying() {
        return underlying;
    }

    @Override
    public FutureDeliveryDates dates() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public <R> R handleWith(final EquityDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
