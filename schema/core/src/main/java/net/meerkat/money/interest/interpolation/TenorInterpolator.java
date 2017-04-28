package net.meerkat.money.interest.interpolation;

import net.meerkat.money.interest.curve.Tenor;
import net.ollie.goat.numeric.interpolation.Interpolator;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public interface TenorInterpolator extends Interpolator<Tenor, Percentage> {

}
