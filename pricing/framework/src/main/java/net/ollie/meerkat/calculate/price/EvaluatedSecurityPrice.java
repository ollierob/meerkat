package net.ollie.meerkat.calculate.price;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public class EvaluatedSecurityPrice<C extends CurrencyId> implements SecurityPrice<C> {

    @XmlElementRef(name = "clean")
    private Money<C> clean;

    @XmlElementRef(name = "dirty")
    private Money<C> dirty;

    @Deprecated
    protected EvaluatedSecurityPrice() {
    }

    public EvaluatedSecurityPrice(Money<C> clean, Money<C> dirty) {
        this.clean = clean;
        this.dirty = dirty;
    }

    @Override
    public Money<C> cleanValue() {
        return clean;
    }

    @Override
    public Money<C> dirtyValue() {
        return dirty;
    }

    @Override
    @Deprecated
    public EvaluatedSecurityPrice<C> evaluate() {
        return this;
    }

}
