package net.meerkat.calculate.price.interest.repo;

import net.meerkat.calculate.price.interest.repo.RepoTypePricer;
import net.meerkat.calculate.price.interest.repo.RepoPricer;

import java.time.temporal.Temporal;

import net.meerkat.instrument.interest.repo.BondRepo;
import net.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public class NativeRepoPricer<T extends Temporal> implements RepoPricer<T> {

    private final RepoTypePricer<T, BondRepo> bondRepoPricer;

    public NativeRepoPricer(final RepoTypePricer<T, BondRepo> bondRepoPricer) {
        this.bondRepoPricer = bondRepoPricer;
    }

    @Override
    public <C extends CurrencyId> RepoPriceContext<C> priceContext(
            final T temporal,
            final C currency) {
        return repo -> bondRepoPricer.price(temporal, repo, currency);
    }

}
