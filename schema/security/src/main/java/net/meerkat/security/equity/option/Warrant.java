package net.meerkat.security.equity.option;

import javax.xml.bind.annotation.XmlElement;

import net.meerkat.security.derivative.option.AbstractOption;
import net.meerkat.security.equity.EquityDerivative;
import net.meerkat.security.equity.Stock;

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
