package net.ollie.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class USD extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    USD() {
    }

    @Override
    public String symbol() {
        return "$";
    }

    @Override
    public String name() {
        return "United States dollar";
    }

}
