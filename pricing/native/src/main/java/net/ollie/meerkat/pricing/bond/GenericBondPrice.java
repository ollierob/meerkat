package net.ollie.meerkat.pricing.bond;

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
    public Money<C> parValue() {
        return par;
    }

    @Override
    public Money<C> cleanValue() {
        return clean;
    }

    @Override
    public Percentage clean() {
        return new Percentage(par.amount().doubleValue() / clean.amount().doubleValue());
    }

    @Override
    public Money<C> dirtyValue() {
        return dirty;
    }

    @Override
    public Percentage dirty() {
        return new Percentage(par.amount().doubleValue() / dirty.amount().doubleValue());
    }

}
