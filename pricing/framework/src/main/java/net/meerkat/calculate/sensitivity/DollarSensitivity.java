package net.meerkat.calculate.sensitivity;

import java.util.Objects;

import net.meerkat.identifier.currency.CurrencyIso;
import net.meerkat.identifier.currency.USD;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public abstract class DollarSensitivity<T extends DollarSensitivity<T>>
        implements Sensitivity.Summable<T> {

    protected static final Money<USD> ZERO_USD = Money.zero(CurrencyIso.USD);

    private final Money<USD> dollars;

    protected DollarSensitivity(final Money<USD> dollars) {
        this.dollars = dollars;
    }

    public Money<USD> value() {
        return dollars;
    }

    protected abstract T with(Money<USD> dollars);

    @Override
    public T plus(final T that) {
        return that == null || that.value().isZero()
                ? this.self()
                : this.with(this.value().plus(that.value()));
    }

    @Override
    public T times(final Number that) {
        return this.with(this.value().times(that));
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
