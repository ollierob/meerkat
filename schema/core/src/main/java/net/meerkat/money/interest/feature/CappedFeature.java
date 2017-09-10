package net.meerkat.money.interest.feature;

import net.meerkat.utils.comparators.Comparators;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public class CappedFeature implements RateFeature {

    private final Percentage maxRate;

    public CappedFeature(final Percentage maxRate) {
        this.maxRate = maxRate;
    }

    @Override
    public Percentage apply(final Percentage rate) {
        return Comparators.min(maxRate, rate);
    }

}
