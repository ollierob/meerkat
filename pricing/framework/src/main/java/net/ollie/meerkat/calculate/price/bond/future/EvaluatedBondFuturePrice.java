package net.ollie.meerkat.calculate.price.bond.future;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import net.ollie.meerkat.calculate.price.EvaluatedSecurityPrice;
import net.ollie.goat.currency.CurrencyId;
import net.ollie.goat.money.Money;

/**
 *
 * @author ollie
 */
public class EvaluatedBondFuturePrice<C extends CurrencyId>
        extends EvaluatedSecurityPrice<C>
        implements BondFuturePrice<C> {

    private static final long serialVersionUID = 1L;

    private CheapestToDeliver<C> cheapestToDeliver;

    @Deprecated
    protected EvaluatedBondFuturePrice() {
    }

    public EvaluatedBondFuturePrice(final Money<C> clean, final Money<C> dirty, final CheapestToDeliver<C> cheapestToDeliver) {
        super(clean, dirty);
        this.cheapestToDeliver = cheapestToDeliver;
    }

    @Override
    public CheapestToDeliver<C> cheapestToDeliver() {
        return cheapestToDeliver;
    }

    @Override
    @Deprecated
    public EvaluatedBondFuturePrice<C> evaluate() {
        return this;
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(cheapestToDeliver);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        cheapestToDeliver = (CheapestToDeliver<C>) in.readObject();
    }

}
