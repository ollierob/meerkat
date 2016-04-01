package net.ollie.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ollie
 */
@XmlTransient
abstract class AbstractCurrencyIso implements Currency, CurrencyIso {

    private static final long serialVersionUID = 1L;

    @Override
    public String value() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return this.value();
    }

}
