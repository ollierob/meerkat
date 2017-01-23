package net.meerkat.instrument.fx.option;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.instrument.derivative.option.AbstractOption;
import net.meerkat.instrument.fx.FxDerivative;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class FxOption
        extends AbstractOption<FxOptionRate<?, ?>>
        implements FxDerivative {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "rate")
    private FxOptionRate<?, ?> rate;

    @Deprecated
    FxOption() {
    }

    @Override
    public FxOptionRate<?, ?> underlying() {
        return rate;
    }

    @Override
    public <R> R handleWith(final FxDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
