package net.meerkat.calculate.sensitivity.greek;

import net.meerkat.calculate.sensitivity.DollarDuration;
import net.meerkat.calculate.sensitivity.Sensitivity;

/**
 * Price sensitivity of derivatives.
 *
 * @author Ollie
 */
public interface Greek<T> extends Sensitivity<T> {

    Delta DELTA = new Delta();
    DollarDuration DV01 = new DollarDuration();

    Rho RHO = new Rho();

}
