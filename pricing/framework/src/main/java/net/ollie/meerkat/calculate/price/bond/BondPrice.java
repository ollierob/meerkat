package net.ollie.meerkat.calculate.price.bond;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.SecurityPrice;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public interface BondPrice<C extends CurrencyId> extends SecurityPrice<C> {

    @Nonnull
    Money<C> parValue();

    @Override
    Money<C> cleanValue();

    @Nonnull
    default Percentage clean() {
        return new Percentage(this.parValue().amount().doubleValue() / this.cleanValue().amount().doubleValue());
    }

    @Override
    Money<C> dirtyValue();

    @Nonnull
    default Percentage dirty() {
        return new Percentage(this.parValue().amount().doubleValue() / this.dirtyValue().amount().doubleValue());
    }

}
