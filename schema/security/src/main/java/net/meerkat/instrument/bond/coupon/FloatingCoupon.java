package net.meerkat.instrument.bond.coupon;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

import net.meerkat.money.interest.InterestRate;
import net.meerkat.numeric.interest.InterestRateId;
import net.ollie.goat.numeric.percentage.Percentage;
import net.meerkat.numeric.interest.HasInterestRateId;
import net.meerkat.numeric.interest.feature.RateFeature;
import net.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public class FloatingCoupon extends AbstractBondCoupon implements HasInterestRateId {

    private final InterestRateId key;
    private final Percentage spread;
    private final Set<? extends RateFeature> features;

    public FloatingCoupon(
            final LocalDate paymentDate,
            final Percentage spread,
            final InterestRateId key,
            final Set<? extends RateFeature> features) {
        super(paymentDate);
        this.key = key;
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
    public InterestRateId interestRateId() {
        return key;
    }

    @Override
    public InterestRate interestRate(final Function<? super InterestRateId, ? extends InterestRate> getRate) {
        Percentage spread = this.spread();
        for (final RateFeature feature : this.features) {
            spread = feature.apply(spread);
        }
        return getRate.apply(key).plus(spread);
    }

    @Override
    public CurrencyId currencyId() {
        return key.currencyId();
    }

}
