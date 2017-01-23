package net.meerkat.instrument.equity.future;

import javax.xml.bind.annotation.XmlElement;

import net.meerkat.instrument.derivative.forward.AbstractFuture;
import net.meerkat.instrument.equity.EquityDerivative;
import net.meerkat.instrument.equity.StockIndex;
import net.meerkat.instrument.derivative.forward.FutureDeliveryDates;

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
