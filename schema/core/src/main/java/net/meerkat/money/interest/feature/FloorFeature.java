package net.meerkat.money.interest.feature;

import net.meerkat.numeric.percentage.Percentage;
import net.meerkat.objects.comparators.Comparators;

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
