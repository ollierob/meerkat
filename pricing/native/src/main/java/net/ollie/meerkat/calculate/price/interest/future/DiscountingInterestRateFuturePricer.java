package net.ollie.meerkat.calculate.price.interest.future;

import java.time.LocalDate;
import java.util.function.BiFunction;

import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.money.currency.Currency;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.interest.InterestRate;
import net.ollie.goat.numeric.percentage.Percentage;
import net.meerkat.security.interest.future.InterestRateFuture;

import org.apache.commons.math3.fraction.Fraction;

/**
 *
 * @author ollie
 */
public class DiscountingInterestRateFuturePricer implements InterestRateFuturePricer<LocalDate> {

    private final BiFunction<? super LocalDate, ? super Currency, ? extends InterestRate> getInterestRate;

    public DiscountingInterestRateFuturePricer(
            final BiFunction<? super LocalDate, ? super Currency, ? extends InterestRate> getInterestRate) {
        this.getInterestRate = getInterestRate;
    }

    @Override
    public <C extends Currency> InterestRateFuturePrice<C> price(
            final LocalDate date,
            final InterestRateFuture future,
            final C currency) {

        final Fraction accrual = future.underlying().accrual();
        final LocalDate expiryDate = future.dates().expiry();

        throw new UnsupportedOperationException("Not supported yet.");

    }

    static final class Price<F extends Currency, C extends Currency> implements InterestRateFuturePrice<C> {

        private LocalDate valuationDate;
        private ExchangeRate<F, C> exchangeRate;
        private Money<F> notional;
        private InterestRate interestRate;
        private InterestRateFutureShifts shifts;

        @Nonnull
        Percentage impliedForwardRate() {
            throw new UnsupportedOperationException(); //TODO 
        }

        @Override
        public Percentage price() {
            return this.impliedForwardRate().inverse();
        }

        @Override
        public Money<C> multiplier() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
