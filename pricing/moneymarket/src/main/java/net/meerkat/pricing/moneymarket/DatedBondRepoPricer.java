package net.meerkat.pricing.moneymarket;

import java.time.LocalDate;
import java.util.Map;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.InstrumentException;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.instrument.bond.BondProvider;
import net.meerkat.instrument.repo.BondRepo;
import net.meerkat.money.Money;
import net.meerkat.pricing.bond.BondPrice;
import net.meerkat.pricing.bond.GenericBondPricer;
import net.ollie.goat.suppliers.lazy.Lazy;

/**
 *
 * @author Ollie
 */
public class DatedBondRepoPricer implements BondRepoPricer<LocalDate> {

    private final BondProvider bondProvider;
    private final GenericBondPricer<LocalDate> bondPricer;

    public DatedBondRepoPricer(final BondProvider bondProvider, final GenericBondPricer<LocalDate> bondPricer) {
        this.bondProvider = bondProvider;
        this.bondPricer = bondPricer;
    }

    @Override
    public <C extends CurrencyId> RepoPrice.Shiftable<C> price(
            final LocalDate date,
            final BondRepo repo,
            final C currency)
            throws InstrumentException {
        return new BondRepoPrice<>(date, repo, currency, RepoShifts.none());
    }

    private final class BondRepoPrice<C extends CurrencyId> implements RepoPrice.Shiftable<C> {

        private final LocalDate date;
        private final BondRepo repo;
        private final C currency;
        private final RepoShifts shifts;

        private final Lazy<Bond> bond;
        private final Lazy<BondPrice<C>> bondPrice;

        BondRepoPrice(final LocalDate date, final BondRepo repo, final C currency, final RepoShifts shifts) {
            this.date = date;
            this.repo = repo;
            this.currency = currency;
            this.shifts = shifts;
            this.bond = Lazy.loadOnce(() -> repo.collateral(bondProvider));
            this.bondPrice = Lazy.loadOnce(() -> bondPricer.price(date, this.bond(), currency).shift(shifts));
        }

        @Override
        public Shiftable<C> shift(final RepoShifts shifts) {
            return new BondRepoPrice<>(date, repo, currency, shifts);
        }

        Bond bond() {
            return bond.get();
        }

        BondPrice<C> bondPrice() {
            return bondPrice.get();
        }

        @Override
        public Money<C> value() {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public Map<String, Object> explain() {
            return this.explanationBuilder()
                    .put("date", date)
                    .put("repo", repo)
                    .put("shifts", shifts)
                    .put("bond", this.bond())
                    .put("bond price", this.bondPrice());
        }

    }

}
