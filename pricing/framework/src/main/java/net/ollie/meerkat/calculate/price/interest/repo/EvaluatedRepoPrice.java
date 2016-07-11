package net.ollie.meerkat.calculate.price.interest.repo;

import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.money.currency.Currency;
import net.ollie.goat.money.Money;
import net.ollie.meerkat.calculate.price.EvaluatedSecurityPrice;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class EvaluatedRepoPrice<C extends Currency>
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
