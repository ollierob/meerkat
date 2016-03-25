package net.ollie.meerkat.calculate.var;

import net.ollie.meerkat.numeric.money.Money;

/**
 * Value at risk (VaR).
 *
 * @author Ollie
 */
public interface Var {

    Money atRisk();

}
