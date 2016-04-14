package net.ollie.meerkat.calculate.price.interest.future;

import java.time.LocalDate;
import java.util.function.BiFunction;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.InterestRate;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.interest.future.InterestRateFuture;

import org.apache.commons.math3.fraction.Fraction;

/**
 *
 * @author ollie
 */
public class DiscountingInterestRateFuturePricer implements InterestRateFuturePricer<LocalDate> {

    private final BiFunction<? super LocalDate, ? super CurrencyId, ? extends InterestRate> getInterestRate;

    public DiscountingInterestRateFuturePricer(
            final BiFunction<? super LocalDate, ? super CurrencyId, ? extends InterestRate> getInterestRate) {
        this.getInterestRate = getInterestRate;
    }

    @Override
    public <C extends CurrencyId> InterestRateFuturePrice<C> price(
            final LocalDate date,
            final InterestRateFuture future,
            final C currency) {

        final Fraction accrual = future.accrualFactor();
        final LocalDate expiryDate = future.dates().expiry();
        final InterestRate rate = future.underlying();

        throw new UnsupportedOperationException("Not supported yet.");

    }

    static final class Price<F extends CurrencyId, C extends CurrencyId> implements InterestRateFuturePrice<C> {

        private final Money<F> notional;

        Price(final Money<F> notional) {
            this.notional = notional;
        }

        @Override
        public Percentage price() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Money<C> multiplier() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
