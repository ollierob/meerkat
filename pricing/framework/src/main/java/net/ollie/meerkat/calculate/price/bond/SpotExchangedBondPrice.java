package net.ollie.meerkat.calculate.price.bond;

import java.time.LocalDate;
import java.util.List;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.numeric.money.fx.ExchangeRate;
import net.ollie.meerkat.security.fx.CashPayment;

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
    public Money<C> parValue() {
        return rate.convert(bondPrice.parValue());
    }

    @Override
    public Money<C> cleanValue() {
        return rate.convert(bondPrice.cleanValue());
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
    public Money<C> dirtyValue() {
        return rate.convert(bondPrice.dirtyValue());
    }

    @Override
    public SpotExchangedBondPrice<F, C> shift(final BondShifts shifts) {
        return new SpotExchangedBondPrice<>(bondPrice.shift(shifts), rate);
    }

}
