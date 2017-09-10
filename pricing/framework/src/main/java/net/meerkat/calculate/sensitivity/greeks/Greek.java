package net.meerkat.calculate.sensitivity.greeks;

import net.meerkat.calculate.sensitivity.Sensitivity;

/**
 *
 * @author Ollie
 */
public interface Greek<T> extends Sensitivity<T> {
    
    int order();

}
