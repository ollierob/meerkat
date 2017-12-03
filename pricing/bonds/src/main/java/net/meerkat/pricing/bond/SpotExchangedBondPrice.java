package net.meerkat.pricing.bond;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.numeric.percentage.Percentage;
import net.meerkat.pricing.bond.shifts.BondShifts;

/**
 *
 * @author ollie
 */
public class SpotExchangedBondPrice<F extends CurrencyId, C extends CurrencyId>
        implements BondPrice.Shiftable<C> {

    private final BondPrice.Shiftable<F> bondPrice;
    private final ExchangeRate<F, C> rate;

    public SpotExchangedBondPrice(final BondPrice.Shiftable<F> bondPrice, final ExchangeRate<F, C> rate) {
        this.bondPrice = bondPrice;
        this.rate = rate;
    }

    @Override
    public Money<C> par() {
        return rate.convertAtMid(bondPrice.par());
    }

    @Override
    public Money<C> clean() {
        return rate.convertAtMid(bondPrice.clean());
    }

    @Override
    public Money<C> accruedInterest() {
        return rate.convertAtMid(bondPrice.accruedInterest());
    }

    @Override
    public Money<C> dirty() {
        return rate.convertAtMid(bondPrice.dirty());
    }

    @Override
    public Percentage yieldToMaturity() {
        return bondPrice.yieldToMaturity();
    }

    @Override
    public SpotExchangedBondPrice<F, C> shift(final BondShifts shifts) {
        return new SpotExchangedBondPrice<>(bondPrice.shift(shifts), rate);
    }

}
