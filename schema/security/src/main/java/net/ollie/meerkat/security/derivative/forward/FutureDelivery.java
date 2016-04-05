package net.ollie.meerkat.security.derivative.forward;

import java.time.LocalDate;

/**
 *
 * @author ollie
 */
public interface FutureDelivery {

    boolean contains(LocalDate date);

}
