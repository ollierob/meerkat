package net.ollie.meerkat.security.bond.coupon;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.meerkat.numeric.Percentage;

/**
 *
 * @author Ollie
 */
public interface BondCoupon {

    @Nonnull
    LocalDate paymentDate();

    @Nonnull
    Percentage spread();

    boolean hasReferenceRate();

}
