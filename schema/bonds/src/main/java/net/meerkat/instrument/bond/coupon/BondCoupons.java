package net.meerkat.instrument.bond.coupon;

import net.coljate.list.List;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.time.calendar.TemporalSequence;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.function.Predicate;

/**
 *
 * @author ollie
 */
public interface BondCoupons<C extends BondCoupon>
        extends TemporalSequence<C>, HasCurrencyId {

    @Nonnull
    List<C> allBetween(LocalDate startInclusive, LocalDate endExclusive);

    @Nonnull
    BondCoupons<C> onOrAfter(LocalDate start);

    @Nonnull
    BondCoupons<C> filter(Predicate<? super C> predicate);

    boolean hasFloatingRateCoupon();

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
            return this.onOrAfter(startInclusive).filter(coupon -> coupon.date().isBefore(endExclusive));
        }

        default Finite<C> allAfter(final LocalDate date) {
            return this.filter(coupon -> coupon.date().isAfter(date));
        }

        @Override
        default Finite<C> onOrAfter(final LocalDate start) {
            return this.filter(coupon -> !coupon.date().isBefore(start));
        }

        @CheckForNull
        default C nextAfter(final LocalDate date) {
            return this.filter(coupon -> coupon.date().isAfter(date)).minBy(BondCoupon.COMPARE_BY_DATE);
        }

        @CheckForNull
        default C nextOnOrAfter(final LocalDate date) {
            return this.filter(coupon -> !coupon.date().isBefore(date)).minBy(BondCoupon.COMPARE_BY_DATE);
        }

        @Override
        default C latestBefore(final LocalDate date) {
            return this.filter(coupon -> !coupon.date().isAfter(date)).maxBy(BondCoupon.COMPARE_BY_DATE);
        }

        @Override
        default C getIfPresent(final Object key) {
            throw new UnsupportedOperationException(); //TODO
        }

    }

}
