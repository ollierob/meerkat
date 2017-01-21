package net.ollie.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class ZAR extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    ZAR() {
    }

    @Override
    public String symbol() {
        return "R";
    }

    @Override
    public String name() {
        return "South African rand";
    }

}
