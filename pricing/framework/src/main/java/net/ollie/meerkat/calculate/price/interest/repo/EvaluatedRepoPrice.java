package net.ollie.meerkat.calculate.price.interest.repo;

import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.money.Money;
import net.ollie.meerkat.calculate.price.EvaluatedSecurityPrice;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class EvaluatedRepoPrice<C extends CurrencyId>
        extends EvaluatedSecurityPrice<C>
        implements RepoPrice<C> {

    private static final long serialVersionUID = 1L;

    @Deprecated
    protected EvaluatedRepoPrice() {
    }

    public EvaluatedRepoPrice(Money<C> clean, Money<C> dirty) {
        super(clean, dirty);
    }

    @Override
    @Deprecated
    public EvaluatedRepoPrice<C> evaluate() {
        return this;
    }

}
