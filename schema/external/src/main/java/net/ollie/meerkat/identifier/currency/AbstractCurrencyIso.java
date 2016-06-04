package net.ollie.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlTransient;

import net.ollie.goat.currency.Currency;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
@XmlTransient
abstract class AbstractCurrencyIso implements Currency, CurrencyIso, HasName {

    private static final long serialVersionUID = 1L;

    @Override
    public String uniqueSymbol() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public String value() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return this.value();
    }

    public Percentage pip() {
        return Percentage.oneBasisPoint();
    }

}
