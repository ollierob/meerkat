package net.ollie.meerkat.identifier.security;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * VALOR number.
 *
 * @author ollie
 * <a href="https://en.wikipedia.org/wiki/Valoren_number">VALOR</a>
 */
@XmlRootElement
public class Valoren extends Nsin {

    @Deprecated
    Valoren() {
    }

    public Valoren(final String value) {
        super(value);
    }

}
