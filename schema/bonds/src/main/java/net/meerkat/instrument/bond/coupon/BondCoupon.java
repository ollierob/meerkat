package net.meerkat.instrument.bond.coupon;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.goat.numeric.percentage.Percentage;
import net.meerkat.identifier.currency.HasCurrencyId;

/**
 *
 * @author Ollie
 */
public interface BondCoupon extends HasCurrencyId {

    @Nonnull
    LocalDate paymentDate();

    @Nonnull
    Percentage spread();

    boolean hasReferenceRate();

}
