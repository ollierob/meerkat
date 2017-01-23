package net.meerkat.instrument.derivative.option;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.numeric.quantity.Quantity;
import net.meerkat.instrument.derivative.Derivative;
import net.meerkat.instrument.derivative.option.exercise.OptionExercise;
import net.meerkat.security.Instrument;

/**
 *
 * @author Ollie
 */
public interface Option<S extends Instrument>
        extends Derivative<S> {

    /**
     * @return the price at which the option can be exercised.
     */
    @Nonnull
    Money<?> strike();

    /**
     * @return income received by seller.
     */
    @Nonnull
    Money<?> premium();

    @CheckForNull
    OptionExercise exercise();

    @Nonnull
    Quantity contractMultiplier();

}
