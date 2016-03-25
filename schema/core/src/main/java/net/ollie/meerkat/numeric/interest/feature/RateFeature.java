package net.ollie.meerkat.numeric.interest.feature;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

import net.ollie.meerkat.numeric.Percentage;

/**
 *
 * @author ollie
 */
public interface RateFeature extends UnaryOperator<Percentage> {

    @Override
    Percentage apply(Percentage rate);

    static List<RateFeature> collared(final Percentage minRate, final Percentage maxRate) {
        return Arrays.asList(
                new FloorFeature(minRate),
                new CappedFeature(maxRate));
    }

}
