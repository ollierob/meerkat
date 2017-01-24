package net.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlTransient;

import net.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
@XmlTransient
public abstract class AbstractCurrencyIso
        implements CurrencyId, CurrencyIso, HasName {

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
