package net.ollie.meerkat.money.interest.curve;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import net.ollie.goat.numeric.manifold.Curve;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author Ollie
 */
public interface YieldCurve<K> extends Curve<K, Percentage> {

    @Nonnull
    @CheckReturnValue
    YieldCurve<K> plus(@Nonnull Percentage bump);

}
