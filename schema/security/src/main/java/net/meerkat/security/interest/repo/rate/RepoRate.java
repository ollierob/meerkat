package net.meerkat.security.interest.repo.rate;

import javax.annotation.Nonnull;

import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public interface RepoRate {

    @Nonnull
    FixedInterestRate rate();

    @Nonnull
    default Percentage annualRate() {
        return this.rate().annualRate();
    }

    default boolean isSpecial() {
        return this.rate().isNegative();
    }

}
