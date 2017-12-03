package net.meerkat.instrument.bond;

import net.coljate.list.List;
import net.coljate.list.ListIterator;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.bond.call.BondCall;
import net.meerkat.instrument.bond.coupon.BondCoupon;
import net.meerkat.instrument.bond.coupon.BondCoupons;
import net.meerkat.instrument.bond.dates.ConvertibleBondDates;
import net.meerkat.instrument.equity.Equity;
import net.meerkat.instrument.equity.EquityProvider;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.function.Predicate;

/**
 * @author Ollie
 */
public class ConvertibleBond extends AbstractBond {

    private final ConvertibleBondDates dates;
    private final List<BondCoupon> coupons;
    private final InstrumentId stockId;
    private final BigDecimal conversionRatio;

    public ConvertibleBond(
            final String name,
            final InstrumentIds identifiers, Money<?> par,
            final BondCall call,
            final IssuerId issuer,
            final ConvertibleBondDates dates,
            final List<BondCoupon> coupons,
            final InstrumentId stockId,
            final BigDecimal conversionRatio) {
        super(name, identifiers, par, call, issuer);
        this.dates = dates;
        this.coupons = coupons;
        this.stockId = stockId;
        this.conversionRatio = conversionRatio;
    }

    @Override
    public ConvertibleBondDates dates() {
        return dates;
    }

    @Override
    public ConvertibleBondCoupons coupons() {
        return new ConvertibleBondCoupons(coupons);
    }

    @Nonnull
    public BigDecimal conversionRatio() {
        return conversionRatio;
    }

    @Nonnull
    public InstrumentId stockId() {
        return stockId;
    }

    @Nonnull
    public Equity stock(final EquityProvider equityProvider) {
        return equityProvider.require(stockId);
    }

    @Nonnull
    public Money<?> conversionPrice() {
        return this.par().over(this.conversionRatio());
    }

    @Override
    public <R> R handleWith(final Bond.Handler<R> handler) {
        return handler.handle(this);
    }

    public static class ConvertibleBondCoupons
            implements BondCoupons.Finite<BondCoupon> {

        private final List<BondCoupon> coupons;

        ConvertibleBondCoupons(final List<BondCoupon> coupons) {
            this.coupons = coupons;
        }

        @Override
        public CurrencyId currencyId() {
            return coupons.first().currencyId();
        }

        @Override
        public ConvertibleBondCoupons filter(final Predicate<? super BondCoupon> predicate) {
            return new ConvertibleBondCoupons(coupons.filter(predicate));
        }

        @Override
        public ListIterator<BondCoupon> iterator() {
            return coupons.iterator();
        }

        @Override
        public BondCoupon last() {
            return coupons.last();
        }

    }

}
