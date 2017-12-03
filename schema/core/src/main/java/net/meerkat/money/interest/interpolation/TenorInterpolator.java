package net.meerkat.money.interest.interpolation;

import net.meerkat.money.interest.curve.Tenor;
import net.meerkat.numeric.interpolation.Interpolator;
import net.meerkat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public interface TenorInterpolator extends Interpolator<Tenor, Percentage> {

}
