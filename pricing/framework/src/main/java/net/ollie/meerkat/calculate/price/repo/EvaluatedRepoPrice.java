package net.ollie.meerkat.calculate.price.repo;

import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.calculate.price.EvaluatedSecurityPrice;
import net.ollie.goat.currency.CurrencyId;
import net.ollie.goat.money.Money;

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
