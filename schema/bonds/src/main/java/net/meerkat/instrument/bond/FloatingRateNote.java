package net.meerkat.instrument.bond;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.bond.call.BondCall;
import net.meerkat.instrument.bond.coupon.FloatingCoupon;
import net.meerkat.instrument.bond.dates.MaturingBondDates;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRateId;
import net.meerkat.money.interest.feature.RateFeature;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public class FloatingRateNote extends StraightBond {

    private static final long serialVersionUID = 1L;

    private final CurrencyId couponCurrency;
    private final Percentage spread;
    private final InterestRateId referenceRate;
    private final List<LocalDate> couponDates;
    private final Set<? extends RateFeature> features;

    public FloatingRateNote(CurrencyId couponCurrency, Percentage spread, InterestRateId referenceRate, List<LocalDate> couponDates, Set<? extends RateFeature> features, String name, InstrumentIds identifiers, Money<?> par, MaturingBondDates dates, BondCall call, IssuerId issuer) {
        super(name, identifiers, par, dates, call, issuer);
        this.couponCurrency = couponCurrency;
        this.spread = spread;
        this.referenceRate = referenceRate;
        this.couponDates = couponDates;
        this.features = features;
    }

    @Override
    public FloatingRateNoteCoupons<?> coupons() {
        return new FloatingRateNoteCoupons<>(couponCurrency);
    }

    @Override
    public <R> R handleWith(final Bond.Handler<R> handler) {
        return handler.handle(this);
    }

    public class FloatingRateNoteCoupons<C extends CurrencyId>
            extends StraightBondCoupons<FloatingCoupon> {

        private final C currency;

        FloatingRateNoteCoupons(final C currency) {
            this.currency = currency;
        }

        @Override
        public FloatingCoupon get(final int index) {
            return new FloatingCoupon(currency, couponDates.get(index), spread, referenceRate, features);
        }

        @Override
        public int size() {
            return couponDates.size();
        }

        @Override
        public boolean isEmpty() {
            return couponDates.isEmpty();
        }

        @Override
        public C currencyId() {
            return currency;
        }

    }

}
