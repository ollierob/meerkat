package net.meerkat.identifier.currency;

import net.meerkat.objects.HasName;

import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ollie
 */
@XmlTransient
public abstract class AbstractCurrencyIso
        implements CurrencyIso, HasName {

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
