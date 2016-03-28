package net.ollie.meerkat.pricing.repo;

import java.time.LocalDate;

import net.ollie.meerkat.calculate.price.repo.RepoPrice;
import net.ollie.meerkat.calculate.price.repo.RepoShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.repo.BondRepo;
import net.ollie.meerkat.security.repo.EquityRepo;
import net.ollie.meerkat.calculate.price.repo.RepoTypePricer;
import net.ollie.meerkat.calculate.price.repo.RepoPricer;

/**
 *
 * @author ollie
 */
public class NativeRepoPricer implements RepoPricer {

    private final RepoTypePricer<LocalDate, BondRepo> bondRepoPricer;
    private final RepoTypePricer<LocalDate, EquityRepo> equityRepoPricer;

    public NativeRepoPricer(
            final RepoTypePricer<LocalDate, BondRepo> bondRepoPricer,
            final RepoTypePricer<LocalDate, EquityRepo> equityRepoPricer) {
        this.bondRepoPricer = bondRepoPricer;
        this.equityRepoPricer = equityRepoPricer;
    }

    @Override
    public <C extends CurrencyId> RepoPriceContext<C> priceContext(
            final LocalDate date,
            final C currency,
            final RepoShifts shifts) {
        return new RepoPriceContext<C>() {

            @Override
            public RepoPrice<C> handle(final BondRepo repo) {
                return bondRepoPricer.price(date, repo, currency, shifts);
            }

            @Override
            public RepoPrice<C> handle(final EquityRepo repo) {
                return equityRepoPricer.price(date, repo, currency, shifts);
            }

        };
    }

}
