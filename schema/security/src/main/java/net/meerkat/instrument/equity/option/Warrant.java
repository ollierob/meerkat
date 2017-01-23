package net.meerkat.instrument.equity.option;

import javax.xml.bind.annotation.XmlElement;

import net.meerkat.instrument.derivative.option.AbstractOption;
import net.meerkat.instrument.equity.EquityDerivative;
import net.meerkat.instrument.equity.Stock;

/**
 *
 * @author ollie
 */
public class Warrant
        extends AbstractOption<Stock>
        implements EquityDerivative<Stock> {

    @XmlElement(name = "underlying")
    private Stock underlying;

    @Deprecated
    Warrant() {
    }

    @Override
    public Stock underlying() {
        return underlying;
    }

    @Override
    public <R> R handleWith(final EquityDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
