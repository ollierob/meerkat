package net.ollie.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class CAD extends AbstractCurrencyIso {

    private static final long serialVersionUID = 1L;

    @Override
    public String symbol() {
        return "$";
    }

    @Override
    public String name() {
        return "Canadian Dollar";
    }

}
