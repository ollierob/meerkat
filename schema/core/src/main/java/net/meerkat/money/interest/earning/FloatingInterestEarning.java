package net.meerkat.money.interest.earning;

import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.InterestRateId;
import net.meerkat.money.interest.InterestRateProvider;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public class FloatingInterestEarning implements InterestEarning {

    private final Money<?> notional;
    private final Percentage spread;
    private final InterestRateId basis;

    public FloatingInterestEarning(Money<?> notional, Percentage spread, InterestRateId basis) {
        this.notional = notional;
        this.spread = spread;
        this.basis = basis;
    }

    @Override
    public Money<?> notional() {
        return notional;
    }

    @Override
    public InterestRate rate(final InterestRateProvider rates) {
        return rates.require(basis).plus(spread);
    }

}
