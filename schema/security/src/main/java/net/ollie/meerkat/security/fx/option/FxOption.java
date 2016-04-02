package net.ollie.meerkat.security.fx.option;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.security.derivative.option.AbstractOption;
import net.ollie.meerkat.security.fx.FxDerivative;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class FxOption
        extends AbstractOption<FxOptionRate<?, ?>>
        implements FxDerivative {

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
