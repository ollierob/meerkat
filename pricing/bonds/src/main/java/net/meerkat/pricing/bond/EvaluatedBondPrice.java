package net.meerkat.pricing.bond;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.price.Price;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public class EvaluatedBondPrice<C extends CurrencyId>
        implements BondPrice<C>, Price.Evaluated<C> {

    private final Money<C> par;
    private final Money<C> clean;
    private final Money<C> dirty;
    private final Percentage yieldToMaturity;

    public EvaluatedBondPrice(
            final Money<C> clean,
            final Money<C> dirty,
            final Money<C> par,
            final Percentage yieldToMaturity) {
        this.clean = clean;
        this.dirty = dirty;
        this.par = par;
        this.yieldToMaturity = yieldToMaturity;
    }

    @Override
    public Money<C> clean() {
        return clean;
    }

    @Override
    public Money<C> dirty() {
        return dirty;
    }

    @Override
    public C currencyId() {
        return par.currencyId();
    }

    @Override
    public Money<C> par() {
        return par;
    }

    @Override
    public Percentage yieldToMaturity() {
        return yieldToMaturity;
    }

    @Override
    @Deprecated
    public EvaluatedBondPrice<C> evaluate() {
        return this;
    }

}
