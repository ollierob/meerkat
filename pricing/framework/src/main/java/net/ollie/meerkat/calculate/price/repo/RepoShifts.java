package net.ollie.meerkat.calculate.price.repo;

import java.util.Collections;
import java.util.Map;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.bond.BondShifts;
import net.ollie.meerkat.calculate.price.shifts.ExchangeRateShifts;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.ollie.goat.currency.CurrencyId;
import net.ollie.goat.money.fx.ExchangeRate;
import net.ollie.meerkat.security.repo.rate.RepoRate;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public interface RepoShifts extends SecurityShifts, ExchangeRateShifts {

    @Nonnull
    RepoRate shift(RepoRate rate);

    @Nonnull
    BondShifts bondShifts(@Nonnull Percentage haircut);

    RepoShifts NONE = new RepoShifts() {

        @Override
        public RepoRate shift(final RepoRate rate) {
            return rate;
        }

        @Override
        public <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> shift(final ExchangeRate<F, T> rate) {
            return rate;
        }

        @Override
        public BondShifts bondShifts(@Nonnull final Percentage haircut) {
            return BondShifts.relativePrice(haircut);
        }

        @Override
        public Map<String, Object> explain() {
            return Collections.emptyMap();
        }

    };

}
