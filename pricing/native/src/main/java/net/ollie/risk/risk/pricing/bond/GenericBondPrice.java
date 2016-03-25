package net.ollie.risk.risk.pricing.bond;

import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author Ollie
 */
final class GenericBondPrice<C extends CurrencyId> implements BondPrice<C> {

    private final Money<C> par;
    private final Money<C> clean;
    private final Money<C> dirty;

    GenericBondPrice(final Money<C> par, final Money<C> clean, final Money<C> dirty) {
        this.par = par;
        this.clean = clean;
        this.dirty = dirty;
    }

    @Override
    public Money<C> par() {
        return par;
    }

    @Override
    public Money<C> cleanPrice() {
        return clean;
    }

    @Override
    public Percentage cleanPercentage() {
        return new Percentage(par.amount().doubleValue() / clean.amount().doubleValue());
    }

    @Override
    public Money<C> dirtyPrice() {
        return dirty;
    }

    @Override
    public Percentage dirtyPercentage() {
        return new Percentage(par.amount().doubleValue() / dirty.amount().doubleValue());
    }

}
