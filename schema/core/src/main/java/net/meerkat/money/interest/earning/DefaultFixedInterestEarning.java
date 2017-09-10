package net.meerkat.money.interest.earning;

import net.meerkat.money.Money;
import net.meerkat.money.interest.fixed.FixedInterestRate;

/**
 *
 * @author ollie
 */
public class DefaultFixedInterestEarning implements FixedInterestEarning {

    private final Money<?> notional;
    private final FixedInterestRate rate;

    protected DefaultFixedInterestEarning(final Money<?> notional, final FixedInterestRate rate) {
        this.notional = notional;
        this.rate = rate;
    }

    @Override
    public Money<?> notional() {
        return notional;
    }

    @Override
    public FixedInterestRate rate() {
        return rate;
    }

}
