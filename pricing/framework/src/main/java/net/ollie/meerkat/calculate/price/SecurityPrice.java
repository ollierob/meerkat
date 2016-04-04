package net.ollie.meerkat.calculate.price;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
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
    SecurityPrice<C> shift(@Nonnull SecurityShifts shifts) throws InvalidShiftTypeException;

    class InvalidShiftTypeException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public InvalidShiftTypeException(final String message) {
            super(message);
        }

    }

}
