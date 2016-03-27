package net.ollie.meerkat.calculate.price.repo;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.bond.BondShifts;
import net.ollie.meerkat.calculate.price.shifts.ExchangeRateShifts;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.ExchangeRate;
import net.ollie.meerkat.security.repo.rate.RepoRate;

/**
 *
 * @author ollie
 */
public interface RepoShifts extends SecurityShifts, ExchangeRateShifts {

    @Nonnull
    RepoRate shift(RepoRate rate);

    @Nonnull
    BondShifts bondShifts();

    RepoShifts NONE = new RepoShifts() {

        @Override
        public RepoRate shift(final RepoRate rate) {
            return rate;
        }

        @Override
        public <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shiftExchangeRate(final ExchangeRate<F, T> rate) {
            return rate;
        }

        @Override
        public BondShifts bondShifts() {
            return BondShifts.NONE;
        }

    };

}
