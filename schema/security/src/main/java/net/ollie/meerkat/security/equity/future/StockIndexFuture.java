package net.ollie.meerkat.security.equity.future;

import javax.xml.bind.annotation.XmlElement;

import net.ollie.meerkat.security.derivative.forward.AbstractFuture;
import net.ollie.meerkat.security.equity.EquityDerivative;
import net.ollie.meerkat.security.equity.StockIndex;
import net.ollie.meerkat.security.derivative.forward.FutureDeliveryDates;

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
