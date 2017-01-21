package net.ollie.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class INR extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    INR() {
    }

    @Override
    public String symbol() {
        return "₹";
    }

    @Override
    public String name() {
        return "Indian rupee";
    }

}
