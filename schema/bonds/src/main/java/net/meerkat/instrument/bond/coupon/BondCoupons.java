package net.meerkat.instrument.bond.coupon;

import java.time.LocalDate;
import java.util.function.Predicate;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.coljate.list.List;
import net.coljate.map.Associative;
import net.meerkat.identifier.currency.HasCurrencyId;

/**
 *
 * @author ollie
 */
public interface BondCoupons<C extends BondCoupon>
        extends Associative<LocalDate, C>, HasCurrencyId {

    @Nonnull
    List<C> allBetween(LocalDate startInclusive, LocalDate endExclusive);

    @Nonnull
    BondCoupons<C> onOrAfter(LocalDate start);

    @Nonnull
    BondCoupons<C> filter(Predicate<? super C> predicate);

    boolean hasFloatingRateCoupon();

    @CheckForNull
    C lastOnOrBefore(LocalDate current);

    boolean isFinite();

    interface Finite<C extends BondCoupon> extends BondCoupons<C>, List<C> {

        @Override
        Finite<C> filter(Predicate<? super C> predicate);

        @Override
        default boolean isFinite() {
            return true;
        }

        @Override
        default boolean hasFloatingRateCoupon() {
            return this.serialStream().anyMatch(BondCoupon::hasReferenceRate);
        }

        @Override
        default Finite<C> allBetween(final LocalDate startInclusive, final LocalDate endExclusive) {
            return this.onOrAfter(startInclusive).filter(coupon -> coupon.paymentDate().isBefore(endExclusive));
        }

        default Finite<C> allAfter(final LocalDate date) {
            return this.filter(coupon -> coupon.paymentDate().isAfter(date));
        }

        @Override
        default Finite<C> onOrAfter(final LocalDate start) {
            return this.filter(coupon -> !coupon.paymentDate().isBefore(start));
        }

        @CheckForNull
        default C nextAfter(final LocalDate date) {
            return this.filter(coupon -> coupon.paymentDate().isAfter(date)).minBy(BondCoupon.COMPARE_BY_DATE);
        }

        @CheckForNull
        default C nextOnOrAfter(final LocalDate date) {
            return this.filter(coupon -> !coupon.paymentDate().isBefore(date)).minBy(BondCoupon.COMPARE_BY_DATE);
        }

        @Override
        default C lastOnOrBefore(final LocalDate date) {
            return this.filter(coupon -> !coupon.paymentDate().isAfter(date)).maxBy(BondCoupon.COMPARE_BY_DATE);
        }

        @Override
        default C getIfPresent(final Object key) {
            throw new UnsupportedOperationException(); //TODO
        }

    }

}
