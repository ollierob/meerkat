package net.meerkat.calculate.price.moneymarket;

import java.time.LocalDate;
import java.util.Map;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.InstrumentException;
import net.meerkat.instrument.moneymarket.Repo;
import net.meerkat.money.Money;
import net.meerkat.pricing.InstrumentPriceException;

/**
 *
 * @author ollie
 */
public class DatedRepoPricer implements RepoPricer {

    private final LocalDate valuationDate;

    public DatedRepoPricer(final LocalDate valuationDate) {
        this.valuationDate = valuationDate;
    }

    @Override
    public <C extends CurrencyId> RepoPrice.Shiftable<C> price(
            final Repo<?> repo,
            final C currency)
            throws InstrumentException, InstrumentPriceException {
        return new DatedRepoPrice<>(valuationDate, repo, RepoShifts.none(), currency);
    }

    class DatedRepoPrice<F extends CurrencyId, T extends CurrencyId> implements RepoPrice.Shiftable<T> {

        private final LocalDate date;
        private final Repo<F> repo;
        private final RepoShifts shifts;
        private final T currency;

        DatedRepoPrice(final LocalDate date, final Repo<F> repo, final RepoShifts shifts, final T currency) {
            this.date = date;
            this.repo = repo;
            this.shifts = shifts;
            this.currency = currency;
        }

        @Override
        public T currencyId() {
            return currency;
        }

        @Override
        public Map<String, Object> explain() {
            return this.explanationBuilder()
                    .put("currency", currency)
                    .put("repo", repo)
                    .put("shifts", shifts);
        }

        @Override
        public DatedRepoPrice<F, T> shift(final RepoShifts shifts) {
            return new DatedRepoPrice<>(date, repo, shifts, currency);
        }

        @Override
        public Money<T> value() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
