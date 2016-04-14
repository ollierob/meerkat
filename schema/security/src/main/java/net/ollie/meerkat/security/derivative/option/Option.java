package net.ollie.meerkat.security.derivative.option;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.security.HasOptionSymbol;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.numeric.quantity.Quantity;
import net.ollie.meerkat.security.Security;
import net.ollie.meerkat.security.derivative.Derivative;
import net.ollie.meerkat.security.derivative.option.exercise.OptionExercise;

/**
 *
 * @author Ollie
 */
public interface Option<S extends Security>
        extends Derivative<S>, HasOptionSymbol {

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
