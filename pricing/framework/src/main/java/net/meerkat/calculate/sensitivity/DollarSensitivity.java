package net.meerkat.calculate.sensitivity;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.dollars);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof DollarSensitivity
                && this.equals((DollarSensitivity) obj);
    }

    protected boolean equals(final DollarSensitivity that) {
        return Objects.equals(dollars, that.dollars);
    }

}
