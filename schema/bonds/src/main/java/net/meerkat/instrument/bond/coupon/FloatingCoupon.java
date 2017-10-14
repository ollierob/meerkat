package net.meerkat.instrument.bond.coupon;

import java.time.LocalDate;
import java.util.Optional;

import net.coljate.collection.Collection;
import net.coljate.set.Set;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.HasInterestRateId;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.InterestRateId;
import net.meerkat.money.interest.feature.RateFeature;
import net.ollie.goat.numeric.percentage.Percentage;
import net.meerkat.money.interest.InterestRateSnapshot;

/**
 *
 * @author ollie
 */
public class FloatingCoupon extends AbstractBondCoupon implements HasInterestRateId {

    private final CurrencyId currency;
    private final InterestRateId rateId;
    private final Percentage spread;
    private final Set<? extends RateFeature> features;

    public FloatingCoupon(
            final CurrencyId currency,
            final LocalDate paymentDate,
            final Percentage spread,
            final InterestRateId key,
            final Set<? extends RateFeature> features) {
        super(paymentDate);
        this.currency = currency;
        this.rateId = key;
        this.spread = spread;
        this.features = features;
    }

    public Percentage spread() {
        return spread;
    }

    public Collection<? extends RateFeature> features() {
        return features;
    }

    @Override
    public boolean hasReferenceRate() {
        return true;
    }

    @Override
    @Deprecated
    public InterestRateId rate() {
        return rateId;
    }

    @Override
    public InterestRateId interestRateId() {
        return rateId;
    }

    @Override
    public InterestRate resolve(final InterestRateSnapshot provider) {
        final InterestRate rate = super.rate(provider);
        Percentage spread = this.spread();
        for (final RateFeature feature : this.features) {
            spread = feature.apply(spread); //TODO do we need to apply this to the rate itself rather than the spread?
        }
        return rate.plus(spread);
    }

    @Override
    public Optional<Money<?>> couponValue() {
        return Optional.empty();
    }

    @Override
    public CurrencyId currencyId() {
        return currency;
    }

}
