package net.ollie.meerkat.calculate.price.interest.repo;

import java.util.Collections;
import java.util.Map;

import javax.annotation.Nonnull;

import net.ollie.goat.money.currency.Currency;
import net.ollie.goat.money.fx.ExchangeRate;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.meerkat.calculate.price.bond.BondShifts;
import net.ollie.meerkat.calculate.price.shifts.ExchangeRateShifts;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.ollie.meerkat.security.moneymarket.repo.rate.RepoRate;

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
        public <F extends Currency, T extends Currency> ExchangeRate<F, T> shift(final ExchangeRate<F, T> rate) {
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
