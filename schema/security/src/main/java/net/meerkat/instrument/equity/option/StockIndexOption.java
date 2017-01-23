package net.meerkat.instrument.equity.option;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.instrument.derivative.option.AbstractOption;
import net.meerkat.instrument.equity.EquityDerivative;
import net.meerkat.instrument.equity.StockIndex;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class StockIndexOption
        extends AbstractOption<StockIndex>
        implements EquityDerivative<StockIndex> {

    @XmlElement(name = "underlying")
    private StockIndex underlying;

    @Deprecated
    StockIndexOption() {
    }

    @Override
    public StockIndex underlying() {
        return underlying;
    }

    @Override
    public <R> R handleWith(final EquityDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
