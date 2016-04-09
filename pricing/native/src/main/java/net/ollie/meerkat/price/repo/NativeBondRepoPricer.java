package net.ollie.meerkat.price.repo;

import java.time.LocalDate;

import net.ollie.goat.suppliers.lazy.Lazy;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondPricer;
import net.ollie.meerkat.calculate.price.bond.BondPricer.BondPriceException;
import net.ollie.meerkat.calculate.price.bond.BondShifts;
import net.ollie.meerkat.calculate.price.repo.RepoPrice;
import net.ollie.meerkat.calculate.price.repo.RepoShifts;
import net.ollie.meerkat.calculate.price.repo.RepoTypePricer;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.FixedInterestRate;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.repo.BondRepo;
import net.ollie.meerkat.security.repo.rate.RepoRate;

/**
 *
 * @author ollie
 */
public class NativeBondRepoPricer implements RepoTypePricer<LocalDate, BondRepo> {

    private final BondPricer<LocalDate> bondPricer;

    public NativeBondRepoPricer(final BondPricer<LocalDate> bondPricer) {
        this.bondPricer = bondPricer;
    }

    @Override
    public <C extends CurrencyId> RepoPrice.Shiftable<C> price(
            final LocalDate valuationDate,
            final BondRepo repo,
            final C currency)
            throws RepoPriceException {

        try {
            final RepoRate repoRate = repo.rate();
            final Percentage haircut = repo.haircut();
            final LocalDate near = repo.dates().near();
            final LocalDate far = repo.dates().far().orElseGet(() -> valuationDate.plusDays(1));
            final BondPrice.Shiftable<C> bondPrice = bondPricer.price(valuationDate, repo.collateral(), currency);
            return new BondRepoPrice<>(repoRate, haircut, near, far, bondPrice, RepoShifts.NONE);
        } catch (final BondPriceException bpex) {
            throw new RepoPriceException("Could not price underlying bond!", bpex);
        }

    }

    private static final class BondRepoPrice<C extends CurrencyId>
            implements RepoPrice.Shiftable<C> {

        private final RepoRate repoRate;
        private final Percentage haircut;
        private final LocalDate near, far;
        private final BondPrice.Shiftable<C> bondPrice;
        private final RepoShifts shifts;

        BondRepoPrice(
                final RepoRate repoRate,
                final Percentage haircut,
                final LocalDate near,
                final LocalDate far,
                final BondPrice.Shiftable<C> bondPrice,
                final RepoShifts shifts) {
            this.repoRate = repoRate;
            this.haircut = haircut;
            this.near = near;
            this.far = far;
            this.shifts = shifts;
            this.bondPrice = bondPrice;
        }

        FixedInterestRate repoRate() {
            return shifts.shift(repoRate).rate();
        }

        Money<C> dirtyBondPrice() {
            final BondShifts bondShifts = shifts.bondShifts(haircut);
            return bondPrice.shift(bondShifts).dirtyValue();
        }

        private final Lazy<Money<C>> cleanValue = Lazy.loadOnceNonnull(this::calculateCleanValue);

        @Override
        public Money<C> cleanValue() {
            return cleanValue.get();
        }

        private Money<C> calculateCleanValue() {
            return this.repoRate().accrue(this.dirtyBondPrice(), near, far);
        }

        @Override
        public BondRepoPrice<C> shift(final RepoShifts shifts) {
            return new BondRepoPrice<>(repoRate, haircut, near, far, bondPrice, shifts);
        }

    }

}
