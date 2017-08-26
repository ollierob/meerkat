package net.meerkat.instrument.bond;

import java.math.BigDecimal;
import java.util.function.Predicate;

import javax.annotation.Nonnull;

import net.coljate.list.List;
import net.coljate.list.ListIterator;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.bond.call.BondCall;
import net.meerkat.instrument.bond.coupon.BondCoupon;
import net.meerkat.instrument.bond.coupon.BondCoupons;
import net.meerkat.instrument.bond.dates.ConvertibleBondDates;
import net.meerkat.instrument.equity.CommonStock;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
public class ConvertibleBond extends AbstractBond {

    private static final long serialVersionUID = 1L;

    private final ConvertibleBondDates dates;
    private final List<BondCoupon> coupons;
    private final CommonStock stock;
    private final BigDecimal conversionRatio;

    public ConvertibleBond(
            final String name, InstrumentIds identifiers, Money<?> par, BondCall call, IssuerId issuer,
            final ConvertibleBondDates dates, List<BondCoupon> coupons, CommonStock stock, BigDecimal conversionRatio) {
        super(name, identifiers, par, call, issuer);
        this.dates = dates;
        this.coupons = coupons;
        this.stock = stock;
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
