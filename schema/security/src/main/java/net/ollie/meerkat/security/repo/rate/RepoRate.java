package net.ollie.meerkat.security.repo.rate;

import javax.annotation.Nonnull;

import net.ollie.goat.money.interest.fixed.FixedInterestRate;

/**
 *
 * @author ollie
 */
public interface RepoRate {

    @Nonnull
    FixedInterestRate rate();

    default boolean isSpecial() {
        return this.rate().isNegative();
    }

}
