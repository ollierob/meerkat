package net.meerkat.identifier.security;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * VALOR number.
 *
 * @author ollie
 * <a href="https://en.wikipedia.org/wiki/Valoren_number">VALOR</a>
 */
@XmlRootElement
public class Valoren extends Nsin {

    private static final long serialVersionUID = 1L;

    @Deprecated
    Valoren() {
    }

    public Valoren(final String value) {
        super(value);
    }

}
