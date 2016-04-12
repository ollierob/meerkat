package net.ollie.meerkat.calculate.price;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public class EvaluatedSecurityPrice<C extends CurrencyId>
        implements SecurityPrice<C>, Externalizable {

    private static final long serialVersionUID = 1L;

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

    @Override
    @OverridingMethodsMustInvokeSuper
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(clean);
        out.writeObject(dirty);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    @SuppressWarnings("unchecked")
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        clean = (Money<C>) in.readObject();
        dirty = (Money<C>) in.readObject();
    }

}