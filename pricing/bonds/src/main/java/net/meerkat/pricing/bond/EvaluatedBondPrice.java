package net.meerkat.pricing.bond;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public class EvaluatedBondPrice<C extends CurrencyId>
        implements BondPrice<C> {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "par")
    private Money<C> par;

    private Money<C> clean;

    private Money<C> dirty;

    @XmlElementRef(name = "yield_to_maturity")
    private Percentage yieldToMaturity;

    @Deprecated
    protected EvaluatedBondPrice() {
    }

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Money<C> par() {
        return par;
    }

    @Override
    public Percentage yieldToMaturity() {
        return yieldToMaturity;
    }

}
