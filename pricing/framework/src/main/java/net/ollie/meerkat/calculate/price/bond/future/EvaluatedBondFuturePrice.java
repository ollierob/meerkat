package net.ollie.meerkat.calculate.price.bond.future;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import net.meerkat.money.Money;
import net.ollie.meerkat.calculate.price.EvaluatedInstrumentPrice;
import net.meerkat.instrument.interest.repo.rate.RepoRate;
import net.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author ollie
 */
//TODO XML
public class EvaluatedBondFuturePrice<C extends CurrencyId>
        extends EvaluatedInstrumentPrice<C>
        implements BondFuturePrice<C> {

    private static final long serialVersionUID = 1L;

    private CheapestToDeliver<C> cheapestToDeliver;

    private RepoRate repoRate;

    @Deprecated
    protected EvaluatedBondFuturePrice() {
    }

    public EvaluatedBondFuturePrice(final Money<C> price, final RepoRate repoRate, final CheapestToDeliver<C> cheapestToDeliver) {
        super(price, price);
        this.cheapestToDeliver = cheapestToDeliver;
        this.repoRate = repoRate;
    }

    @Override
    public Money<C> price() {
        return super.clean();
    }

    @Override
    public CheapestToDeliver<C> cheapestToDeliver() {
        return cheapestToDeliver;
    }

    @Override
    public RepoRate repoRate() {
        return repoRate;
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
