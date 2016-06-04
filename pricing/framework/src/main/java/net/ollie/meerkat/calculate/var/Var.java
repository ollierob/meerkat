package net.ollie.meerkat.calculate.var;

import javax.annotation.Nonnull;

import net.ollie.goat.money.Money;

/**
 * Value at risk (VaR).
 *
 * @author Ollie
 */
public interface Var {

    @Nonnull
    Money<?> atRisk();

}
