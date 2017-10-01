package net.meerkat.pricing.bond;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.coljate.list.List;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.instrument.bond.StraightBond;
import net.meerkat.instrument.bond.coupon.BondCoupon;
import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;

/**
 * Pricing helper.
 *
 * @author ollie
 */
public interface StraightBondValuationContext<C extends CurrencyId> extends HasCurrencyId {

    @Override
    C currencyId();

    @Nonnull
    LocalDate valueDate();

    @Nonnull
    StraightBond bond();

    default Money<?> par() {
        return this.bond().par();
    }

    default List<? extends BondCoupon> unpaidCoupons() {
        return this.bond().coupons().allAfter(this.valueDate());
    }

    default BondCoupon previousCoupon() {
        return this.bond().coupons().lastOnOrBefore(this.valueDate());
    }

    default BondCoupon currentCoupon() {
        return this.bond().coupons().nextOnOrAfter(this.valueDate());
    }

    @Nonnull
    InterestRate discountRate();

    default Money<C> discount(final Money<?> money, final LocalDate paymentDate) {
        final Money<?> moneyPv = this.discountRate().discount(money, this.valueDate(), paymentDate, this.interestRateInterpolator());
        return this.convert(moneyPv);
    }

    InterestRateInterpolator interestRateInterpolator();

    <F extends CurrencyId> Money<C> convert(Money<F> money);

    InterestRate interestRate(BondCoupon coupon);

}
