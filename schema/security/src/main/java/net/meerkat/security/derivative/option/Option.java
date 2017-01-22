package net.meerkat.security.derivative.option;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.numeric.quantity.Quantity;
import net.meerkat.security.Security;
import net.meerkat.security.derivative.Derivative;
import net.meerkat.security.derivative.option.exercise.OptionExercise;

/**
 *
 * @author Ollie
 */
public interface Option<S extends Security>
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