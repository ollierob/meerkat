package net.meerkat.instrument.bond;

import net.coljate.list.List;
import net.coljate.list.ListIterator;
import net.coljate.set.Set;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.bond.call.BondCall;
import net.meerkat.instrument.bond.coupon.FloatingCoupon;
import net.meerkat.instrument.bond.dates.MaturingBondDates;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRateId;
import net.meerkat.money.interest.feature.RateFeature;
import net.meerkat.numeric.percentage.Percentage;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Predicate;

/**
 *
 * @author ollie
 */
public class FloatingRateNote extends StraightBond {

    private static final long serialVersionUID = 1L;

    private final CurrencyId couponCurrency;
    private final Percentage spread;
    private final InterestRateId referenceRate;
    private final Period couponFrequency;
    private final List<LocalDate> couponDates;
    private final Set<? extends RateFeature> features;

    public FloatingRateNote(
            final String name,
            final InstrumentIds identifiers,
            final Money<?> par,
            final MaturingBondDates dates,
            final BondCall call,
            final IssuerId issuer,
            final CurrencyId couponCurrency,
            final Percentage spread,
            final InterestRateId referenceRate,
            final Period couponFrequency,
            final List<LocalDate> couponDates,
            final Set<? extends RateFeature> features) {
        super(name, identifiers, par, dates, call, issuer);
        this.couponCurrency = couponCurrency;
        this.spread = spread;
        this.referenceRate = referenceRate;
        this.couponFrequency = couponFrequency;
        this.couponDates = couponDates;
        this.features = features;
    }

    @Override
    public FloatingRateNoteCoupons<?> coupons() {
        return new FloatingRateNoteCoupons<>(couponFrequency, couponCurrency);
    }

    @Override
    public <R> R handleWith(final Bond.Handler<R> handler) {
        return handler.handle(this);
    }

    public class FloatingRateNoteCoupons<C extends CurrencyId>
            extends StraightBondCoupons<FloatingCoupon> {

        private final C currency;

        FloatingRateNoteCoupons(final Period frequency, final C currency) {
            super(frequency);
            this.currency = currency;
        }

        @Override
        public boolean isEmpty() {
            return couponDates.isEmpty();
        }

        @Override
        public C currencyId() {
            return currency;
        }

        @Override
        public FloatingRateNoteCoupons<C> filter(final Predicate<? super FloatingCoupon> predicate) {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public ListIterator<FloatingCoupon> iterator() {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public FloatingCoupon last() {
            return new FloatingCoupon(currency, couponDates.last(), spread, referenceRate, features);
        }

    }

}
