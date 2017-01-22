package net.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class CNY extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    CNY() {
    }

    @Override
    public String symbol() {
        return "¥";
    }

    @Override
    public String name() {
        return "Renminbi";
    }

}
