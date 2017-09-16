package net.meerkat.instrument.interest.swap.leg;

import java.time.LocalDate;
import java.util.Map;

import javax.annotation.Nonnull;

import net.meerkat.Explainable;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyIds;
import net.meerkat.instrument.derivative.swap.SwapLeg;
import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.InterestRateOrId;
import net.meerkat.money.interest.InterestRates;

/**
 *
 * @author ollie
 */
public interface InterestRateSwapLeg<P extends CurrencyId, R extends CurrencyId>
        extends SwapLeg<P, R>, HasCurrencyIds, Explainable {

    @Nonnull
    Money<P> payNotional();

    @Nonnull
    LocalDate payDate();

    @Nonnull
    InterestRateOrId payRate();

    @Nonnull
    Money<R> receiveNotional();

    @Nonnull
    InterestRateOrId receiveRate();

    @Override
    default P payCurrency() {
        return this.payNotional().currencyId();
    }

    @Nonnull
    default InterestRate payRate(final InterestRates interestRateProvider) {
        return this.payRate().resolve(interestRateProvider);
    }

    @Override
    default R receiveCurrency() {
        return this.receiveNotional().currencyId();
    }

    @Nonnull
    default InterestRate receiveRate(final InterestRates interestRateProvider) {
        return this.receiveRate().resolve(interestRateProvider);
    }

    @Override
    default Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("date", this.payDate())
                .put("pay rate", this.payRate())
                .put("pay notional", this.payNotional())
                .put("receive rate", this.receiveRate())
                .put("receive notional", this.receiveNotional());
    }

}