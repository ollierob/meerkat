package net.meerkat.security.bond.coupon;

import java.time.LocalDate;
import static java.util.stream.Collectors.toList;

import javax.annotation.CheckForNull;

import net.meerkat.money.currency.HasCurrency;
import net.ollie.meerkat.utils.collections.sequence.FiniteSequence;
import net.ollie.meerkat.utils.collections.sequence.OrderedSequence;
import net.ollie.meerkat.utils.collections.sequence.StartingSequence;

/**
 *
 * @author ollie
 */
public interface BondCoupons<C extends BondCoupon>
        extends OrderedSequence<LocalDate, C>, StartingSequence<C>, HasCurrency {

    @Override
    FiniteSequence<C> between(LocalDate startInclusive, LocalDate endExclusive);

    boolean hasFloatingRateCoupon();

    @CheckForNull
    C prior(LocalDate current);

    interface Finite<C extends BondCoupon> extends BondCoupons<C>, FiniteSequence<C> {

        @Override
        default boolean hasFloatingRateCoupon() {
            return this.stream().anyMatch(BondCoupon::hasReferenceRate);
        }

        @Override
        default FiniteSequence<C> between(final LocalDate startInclusive, final LocalDate endExclusive) {
            return this.onOrAfter(startInclusive).where(coupon -> coupon.paymentDate().isBefore(endExclusive));
        }

        @Override
        default FiniteSequence<C> onOrAfter(final LocalDate start) {
            return FiniteSequence.of(this.stream()
                    .filter(coupon -> !coupon.paymentDate().isBefore(start))
                    .collect(toList()));
        }

        @Override
        default boolean isEmpty() {
            return this.size() == 0;
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
