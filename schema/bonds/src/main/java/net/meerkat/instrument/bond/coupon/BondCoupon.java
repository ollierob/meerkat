package net.meerkat.instrument.bond.coupon;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.Explainable;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.InterestRateOrId;
import net.meerkat.money.interest.InterestRates;
import net.meerkat.money.interest.exception.UnknownInterestRateException;

/**
 *
 * @author Ollie
 */
public interface BondCoupon extends HasCurrencyId, Explainable {

    Comparator<BondCoupon> COMPARE_BY_DATE = Comparator.comparing(BondCoupon::paymentDate);

    @Nonnull
    LocalDate paymentDate();

    boolean hasReferenceRate();

    @Nonnull
    InterestRateOrId rate();

    @Nonnull
    default InterestRate rate(final InterestRates provider) throws UnknownInterestRateException {
        return this.rate().resolve(provider);
    }

    @Nonnull
    Optional<Money<?>> couponValue();

    @Override
    default Map<String, Object> explain() {
        return this.explanationBuilder().put("payment date", this.paymentDate());
    }

}
