package net.ollie.meerkat.calculate.price.bond;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.SecurityPrice;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public interface BondPrice extends SecurityPrice {

    @Nonnull
    Money par();

    @Override
    Money cleanPrice();

    @Nonnull
    default Percentage cleanPercentage() {
        return new Percentage(this.par().amount().doubleValue() / this.cleanPrice().amount().doubleValue());
    }

    @Override
    Money dirtyPrice();

    @Nonnull
    default Percentage dirtyPercentage() {
        return new Percentage(this.par().amount().doubleValue() / this.dirtyPrice().amount().doubleValue());
    }

}
