package net.ollie.meerkat.calculate.price.bond;

import java.time.LocalDate;
import java.util.List;

import net.ollie.meerkat.money.Money;
import net.ollie.meerkat.money.currency.Currency;
import net.ollie.meerkat.money.fx.ExchangeRate;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.meerkat.security.fx.CashPayment;

/**
 *
 * @author ollie
 */
public class SpotExchangedBondPrice<F extends Currency, C extends Currency>
        implements BondPrice.Shiftable<C> {

    private final BondPrice.Shiftable<F> bondPrice;
    private final ExchangeRate<F, C> rate;

    public SpotExchangedBondPrice(final BondPrice.Shiftable<F> bondPrice, final ExchangeRate<F, C> rate) {
        this.bondPrice = bondPrice;
        this.rate = rate;
    }

    @Override
    public Money<C> par() {
        return rate.convert(bondPrice.par());
    }

    @Override
    public Money<C> clean() {
        return rate.convert(bondPrice.clean());
    }

    @Override
    public List<CashPayment<C>> cleanFlow(final LocalDate start, final LocalDate end) {
        throw new UnsupportedOperationException("Not supported yet."); //TODO
    }

    @Override
    public Money<C> accruedInterest() {
        return rate.convert(bondPrice.accruedInterest());
    }

    @Override
    public Money<C> dirty() {
        return rate.convert(bondPrice.dirty());
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
