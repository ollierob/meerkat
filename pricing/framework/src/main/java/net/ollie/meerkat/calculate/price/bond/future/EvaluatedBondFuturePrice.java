package net.ollie.meerkat.calculate.price.bond.future;

import net.ollie.meerkat.calculate.price.EvaluatedSecurityPrice;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public class EvaluatedBondFuturePrice<C extends CurrencyId>
        extends EvaluatedSecurityPrice<C>
        implements BondFuturePrice<C> {

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

}
