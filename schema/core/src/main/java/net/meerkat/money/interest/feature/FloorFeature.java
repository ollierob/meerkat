package net.meerkat.money.interest.feature;

import net.meerkat.utils.comparators.Comparators;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public class FloorFeature implements RateFeature {

    private final Percentage minRate;

    public FloorFeature(final Percentage minRate) {
        this.minRate = minRate;
    }

    @Override
    public Percentage apply(final Percentage rate) {
        return Comparators.max(minRate, rate);
    }

}
