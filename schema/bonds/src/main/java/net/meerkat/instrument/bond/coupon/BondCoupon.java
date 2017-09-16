package net.meerkat.instrument.bond.coupon;

import java.time.LocalDate;
import java.util.Map;

import javax.annotation.Nonnull;

import net.meerkat.Explainable;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.InterestRateOrId;
import net.meerkat.money.interest.UnknownInterestRateException;
import net.ollie.goat.numeric.percentage.Percentage;
import net.meerkat.money.interest.InterestRates;

/**
 *
 * @author Ollie
 */
public interface BondCoupon extends HasCurrencyId, Explainable {

    @Nonnull
    LocalDate paymentDate();

    @Nonnull
    Percentage spread();

    boolean hasReferenceRate();

    @Nonnull
    InterestRateOrId rate();

    @Nonnull
    default InterestRate rate(final InterestRates provider) throws UnknownInterestRateException {
        return this.rate().resolve(provider).plus(this.spread());
    }

    @Override
    default Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("payment date", this.paymentDate())
                .put("spread", this.spread());
    }

}
