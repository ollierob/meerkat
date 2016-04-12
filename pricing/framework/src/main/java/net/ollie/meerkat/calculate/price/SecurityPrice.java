package net.ollie.meerkat.calculate.price;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public interface SecurityPrice<C extends CurrencyId> {

    @Nonnull
    Money<C> cleanValue();

    @Nonnull
    Money<C> dirtyValue();

    @Nonnull
    default EvaluatedSecurityPrice<C> evaluate() {
        return new EvaluatedSecurityPrice<>(this.cleanValue(), this.dirtyValue());
    }

    class InvalidShiftTypeException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public InvalidShiftTypeException(final String message) {
            super(message);
        }

    }

}
