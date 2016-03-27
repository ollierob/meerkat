package net.ollie.risk.risk.pricing.repo;

import java.time.LocalDate;

import net.ollie.meerkat.calculate.price.repo.RepoPrice;
import net.ollie.meerkat.calculate.price.repo.RepoPriceHandler;
import net.ollie.meerkat.calculate.price.repo.RepoPricer;
import net.ollie.meerkat.calculate.price.repo.RepoShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.repo.BondRepo;
import net.ollie.meerkat.security.repo.EquityRepo;

/**
 *
 * @author ollie
 */
public class NativeRepoPricerHandler implements RepoPriceHandler {

    private final RepoPricer<LocalDate, BondRepo> bondRepoPricer;
    private final RepoPricer<LocalDate, EquityRepo> equityRepoPricer;

    public NativeRepoPricerHandler(
            final RepoPricer<LocalDate, BondRepo> bondRepoPricer,
            final RepoPricer<LocalDate, EquityRepo> equityRepoPricer) {
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
