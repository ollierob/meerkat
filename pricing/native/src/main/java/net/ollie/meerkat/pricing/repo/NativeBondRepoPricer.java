package net.ollie.meerkat.pricing.repo;

import java.time.LocalDate;

import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondPriceHandler;
import net.ollie.meerkat.calculate.price.bond.BondShifts;
import net.ollie.meerkat.calculate.price.repo.RepoPrice;
import net.ollie.meerkat.calculate.price.repo.RepoPricer;
import net.ollie.meerkat.calculate.price.repo.RepoShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.interest.InterestRate;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.Bond;
import net.ollie.meerkat.security.repo.BondRepo;

/**
 *
 * @author ollie
 */
public class NativeBondRepoPricer implements RepoPricer<LocalDate, BondRepo> {

    private final BondPriceHandler bondPricer;

    public NativeBondRepoPricer(final BondPriceHandler bondPricer) {
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

        final InterestRate rate = shifts.shift(repo.rate()).rate();

        final LocalDate near = repo.dates().near();
        final LocalDate far = repo.dates().far().orElse(date.plusDays(1)); //TODO open repo
        final Money<C> repoPrice = rate.accrue(dirtyBondPrice, near, far);
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
