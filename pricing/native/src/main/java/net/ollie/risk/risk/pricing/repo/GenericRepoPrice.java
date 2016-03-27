package net.ollie.risk.risk.pricing.repo;

import net.ollie.meerkat.calculate.price.repo.RepoPrice;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
class GenericRepoPrice<C extends CurrencyId> implements RepoPrice<C> {

    private final Money<C> clean;

    GenericRepoPrice(final Money<C> clea) {
        this.clean = clea;
    }

    @Override
    public Money<C> cleanValue() {
        return clean;
    }

}
