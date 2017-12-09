package net.meerkat.pricing.moneymarket;

import net.meerkat.functions.suppliers.lazy.Lazy;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.instrument.bond.BondSnapshot;
import net.meerkat.instrument.repo.BondRepo;
import net.meerkat.instrument.repo.repurchase.BuySellBackRepurchase;
import net.meerkat.instrument.repo.repurchase.ClassicRepoRepurchase;
import net.meerkat.instrument.repo.repurchase.OpenRepoRepurchase;
import net.meerkat.instrument.repo.repurchase.RepoRepurchase;
import net.meerkat.money.Money;
import net.meerkat.pricing.bond.BondPrice;
import net.meerkat.pricing.bond.BondPriceException;
import net.meerkat.pricing.bond.GenericBondPricer;
import net.meerkat.pricing.moneymarket.shifts.RepoShifts;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;

import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author Ollie
 */
public class DailyBondRepoPricer implements BondRepoPricer<LocalDate> {

    private final BondSnapshot bondProvider;
    private final GenericBondPricer<LocalDate> bondPricer;

    public DailyBondRepoPricer(final BondSnapshot bondProvider, final GenericBondPricer<LocalDate> bondPricer) {
        this.bondProvider = bondProvider;
        this.bondPricer = bondPricer;
    }

    @Override
    public <C extends CurrencyId> RepoPrice.Shiftable<C> price(
            final LocalDate date,
            final BondRepo<?> repo,
            final C currency,
            final InstrumentPriceShifts shifts)
            throws BondPriceException {
        return new BondRepoPrice<>(date, repo, currency, RepoShifts.none());
    }

    private final class BondRepoPrice<C extends CurrencyId> implements RepoPrice.Shiftable<C> {

        private final LocalDate date;
        private final BondRepo<?> repo;
        private final C currency;
        private final RepoShifts shifts;

        private final Lazy<Bond> bond;
        private final Lazy<BondPrice<C>> bondPrice;

        BondRepoPrice(final LocalDate date, final BondRepo<?> repo, final C currency, final RepoShifts shifts) {
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
            final BondPrice<C> bondPrice = this.bondPrice();
            return repo.repurchase().handleWith(new RepurchaseValueHandler<>(date, bondPrice));
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

    private static class RepurchaseValueHandler<C extends CurrencyId> implements RepoRepurchase.Handler<Money<C>> {

        private final LocalDate valueDate;
        private final BondPrice<C> bondPrice;

        RepurchaseValueHandler(final LocalDate valueDate, final BondPrice<C> bondPrice) {
            this.valueDate = valueDate;
            this.bondPrice = bondPrice;
        }

        @Override
        public Money<C> handle(final OpenRepoRepurchase openRepurchase) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Money<C> handle(final ClassicRepoRepurchase classicRepurchase) {
            return classicRepurchase.accrueFrom(bondPrice.dirty(), valueDate);
        }

        @Override
        public Money<C> handle(final BuySellBackRepurchase<?> buySellBack) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
