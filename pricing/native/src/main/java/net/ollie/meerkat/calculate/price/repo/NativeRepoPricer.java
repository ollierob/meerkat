package net.ollie.meerkat.calculate.price.repo;

import java.time.temporal.Temporal;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.repo.BondRepo;

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
