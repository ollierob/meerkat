package net.ollie.meerkat.security.derivative.forward;

import javax.annotation.Nonnull;

import net.ollie.meerkat.security.Security;

/**
 *
 * @author ollie
 */
public interface Future<S extends Security> extends Forward<S> {

    @Nonnull
    FutureDeliveryDates deliveryDates();

}
