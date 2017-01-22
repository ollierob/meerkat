package net.ollie.meerkat.calculate.price.bond;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.money.Money;
import net.meerkat.money.currency.Currency;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.meerkat.calculate.price.EvaluatedSecurityPrice;

/**
 *
 * @author ollie
 */
public class EvaluatedBondPrice<C extends Currency>
        extends EvaluatedSecurityPrice<C>
        implements BondPrice<C> {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "par")
    private Money<C> par;

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
        super(clean, dirty);
        this.par = par;
        this.yieldToMaturity = yieldToMaturity;
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
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(par);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        par = (Money<C>) in.readObject();
    }

}
