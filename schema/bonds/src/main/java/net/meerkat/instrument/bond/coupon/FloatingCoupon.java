package net.meerkat.instrument.bond.coupon;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.interest.HasInterestRateId;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.InterestRateId;
import net.meerkat.money.interest.InterestRateOrId;
import net.meerkat.money.interest.InterestRateProvider;
import net.meerkat.money.interest.feature.RateFeature;
import net.ollie.goat.numeric.percentage.Percentage;

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

    @Override
    public Percentage spread() {
        return spread;
    }

    public Collection<? extends RateFeature> features() {
        return Collections.unmodifiableCollection(features);
    }

    @Override
    public boolean hasReferenceRate() {
        return true;
    }

    @Override
    public InterestRateOrId rate() {
        return rateId;
    }

    @Override
    public InterestRateId interestRateId() {
        return rateId;
    }

    @Override
    public InterestRate resolve(final InterestRateProvider provider) {
        final InterestRate rate = super.rate(provider);
        if (rate == null) {
            return null;
        }
        Percentage spread = this.spread();
        for (final RateFeature feature : this.features) {
            spread = feature.apply(spread);
        }
        return rate.plus(spread);
    }

    @Override
    public CurrencyId currencyId() {
        return currency;
    }

}
