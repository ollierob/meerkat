package net.ollie.meerkat.security.bond.coupon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

import javax.annotation.CheckForNull;

import net.ollie.meerkat.utils.collections.FiniteSequence;
import net.ollie.meerkat.utils.time.TemporalSequence;

/**
 *
 * @author ollie
 */
public interface BondCoupons<C extends BondCoupon> extends TemporalSequence<LocalDate, C> {

    @Override
    default List<C> between(final LocalDate startInclusive, final LocalDate endExclusive) {
        final List<C> coupons = new ArrayList<>();
        this.onOrAfter(startInclusive).until(coupon -> !endExclusive.isAfter(coupon.paymentDate()), coupons::add);
        return coupons;
    }

    boolean hasFloatingRateCoupon();

    @CheckForNull
    C prior(LocalDate current);

    interface Finite<C extends BondCoupon> extends BondCoupons<C>, List<C> {

        default int numCoupons() {
            return this.size();
        }

        @Override
        default boolean hasFloatingRateCoupon() {
            return this.stream().anyMatch(BondCoupon::hasReferenceRate);
        }

        @Override
        default FiniteSequence<C> onOrAfter(final LocalDate start) {
            return FiniteSequence.of(this.stream()
                    .filter(coupon -> !coupon.paymentDate().isBefore(start))
                    .collect(toList()));
        }

        @Override
        default boolean isEmpty() {
            return this.numCoupons() == 0;
        }

        @Override
        default C prior(final LocalDate current) {
            C previous = null;
            for (final C coupon : this) {
                if (coupon.paymentDate().isAfter(current)) {
                    return previous;
                }
                previous = coupon;
            }
            return null;
        }

    ;

}

}
