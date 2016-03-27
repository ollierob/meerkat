package net.ollie.risk.risk.pricing.repo;

import java.time.LocalDate;

import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondPriceHandler;
import net.ollie.meerkat.calculate.price.bond.BondShifts;
import net.ollie.meerkat.calculate.price.repo.RepoPrice;
import net.ollie.meerkat.calculate.price.repo.RepoPricer;
import net.ollie.meerkat.calculate.price.repo.RepoShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.Bond;
import net.ollie.meerkat.security.repo.BondRepo;
import net.ollie.meerkat.security.repo.rate.RepoRate;

/**
 *
 * @author ollie
 */
public class BondRepoPricer implements RepoPricer<LocalDate, BondRepo> {

    private final BondPriceHandler bondPricer;

    public BondRepoPricer(final BondPriceHandler bondPricer) {
        this.bondPricer = bondPricer;
    }

    @Override
    public <C extends CurrencyId> RepoPrice<C> price(
            final LocalDate date,
            final BondRepo repo,
            final C currency,
            final RepoShifts shifts) {

        final BondPrice<C> bondPrice = this.price(repo.collateral(), date, currency, shifts.bondShifts());
        final Money<C> dirtyBondPrice = bondPrice.dirtyValue();

        final RepoRate rate = shifts.shift(repo.rate());

        final LocalDate far = repo.dates().far().orElse(date.plusDays(1)); //TODO open repo
        final Money<C> repoPrice = rate.accrue(dirtyBondPrice, date, far);
        return new GenericRepoPrice<>(repoPrice);

    }

    private <C extends CurrencyId> BondPrice<C> price(
            final Bond bond,
            final LocalDate date,
            final C currency,
            final BondShifts shifts) {
        return bondPricer.price(date, bond, shifts, currency);
    }

}
