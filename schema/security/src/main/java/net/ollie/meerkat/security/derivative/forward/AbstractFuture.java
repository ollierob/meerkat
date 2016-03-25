package net.ollie.meerkat.security.derivative.forward;

import javax.xml.bind.annotation.XmlElement;

import net.ollie.meerkat.identifier.security.FutureTicker;
import net.ollie.meerkat.security.Security;
import net.ollie.meerkat.security.NamedSecurity;

/**
 *
 * @author Ollie
 */
public abstract class AbstractFuture<S extends Security>
        extends NamedSecurity
        implements Future<S> {

    @XmlElement(name = "ticker")
    private FutureTicker ticker;

    @Deprecated
    protected AbstractFuture() {
    }

    public AbstractFuture(final FutureTicker ticker, final String name) {
        super(name);
        this.ticker = ticker;
    }

    @Override
    public FutureTicker ticker() {
        return ticker;
    }

}
