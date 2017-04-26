package net.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class SEK extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    SEK() {
    }

    @Override
    public String symbol() {
        return "kr";
    }

    @Override
    public String name() {
        return "Swedish krona";
    }

}
