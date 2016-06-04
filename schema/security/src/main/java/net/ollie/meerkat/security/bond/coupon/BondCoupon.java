package net.ollie.meerkat.security.bond.coupon;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.goat.money.currency.HasCurrency;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author Ollie
 */
public interface BondCoupon extends HasCurrency {

    @Nonnull
    LocalDate paymentDate();

    @Nonnull
    Percentage spread();

    boolean hasReferenceRate();

}
