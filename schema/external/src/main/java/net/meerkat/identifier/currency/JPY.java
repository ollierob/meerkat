package net.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class JPY extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    JPY() {
    }

    @Override
    public String symbol() {
        return "¥";
    }

    @Override
    public String uniqueSymbol() {
        return "JP¥";
    }

    @Override
    public String name() {
        return "Japanese yen";
    }

}
