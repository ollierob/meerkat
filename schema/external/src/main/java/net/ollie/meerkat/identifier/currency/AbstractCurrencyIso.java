package net.ollie.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlTransient;

import net.ollie.meerkat.money.currency.Currency;
import net.ollie.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
@XmlTransient
public abstract class AbstractCurrencyIso
        implements Currency, CurrencyIso, HasName {

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
