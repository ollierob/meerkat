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

    @XmlElement(name = "notional")
    private FxOptionRate<?, ?> notional;

    @Deprecated
    FxOption() {
    }

    @Override
    public FxOptionRate<?, ?> underlying() {
        return notional;
    }

    @Override
    public <R> R handleWith(final FxDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
