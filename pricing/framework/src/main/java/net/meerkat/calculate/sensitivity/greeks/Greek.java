package net.meerkat.calculate.sensitivity.greeks;

import net.meerkat.calculate.sensitivity.Sensitivity;

/**
 *
 * @author Ollie
 */
public interface Greek<G extends Greek<G>> extends Sensitivity<G> {

    int order();

}
