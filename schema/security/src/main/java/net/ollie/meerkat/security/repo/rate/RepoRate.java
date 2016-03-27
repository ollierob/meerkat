package net.ollie.meerkat.security.repo.rate;

import net.ollie.meerkat.numeric.interest.FixedInterestRate;

/**
 *
 * @author ollie
 */
public interface RepoRate extends FixedInterestRate {

    default boolean isSpecial() {
        return this.isNegative();
    }

}
