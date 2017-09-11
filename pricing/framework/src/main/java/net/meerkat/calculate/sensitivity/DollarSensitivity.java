package net.meerkat.calculate.sensitivity;

import net.meerkat.identifier.currency.USD;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public abstract class DollarSensitivity implements Sensitivity {

    private final Money<USD> dollars;

    protected DollarSensitivity(final Money<USD> dollars) {
        this.dollars = dollars;
    }

    public Money<USD> value() {
        return dollars;
    }

}
