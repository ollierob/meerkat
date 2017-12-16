package net.meerkat.instrument.bond.coupon;

import net.meerkat.Explainable;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.InterestRateOrId;
import net.meerkat.money.interest.InterestRateSnapshot;
import net.meerkat.money.interest.exception.UnknownInterestRateException;
import net.meerkat.temporal.date.HasDate;

import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Ollie
 */
public interface BondCoupon extends HasDate, HasCurrencyId, Explainable {

    Comparator<BondCoupon> COMPARE_BY_DATE = Comparator.comparing(BondCoupon::date);

    boolean hasReferenceRate();

    @Nonnull
    InterestRateOrId rate();

    @Nonnull
    default InterestRate rate(final InterestRateSnapshot provider) throws UnknownInterestRateException {
        return this.rate().resolve(provider);
    }

    @Nonnull
    Optional<Money<?>> couponValue();

    @Override
    default Map<String, Object> explain() {
        return this.explanationBuilder().put("payment date", this.date());
    }

}
