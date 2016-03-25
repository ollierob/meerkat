package net.ollie.meerkat.security.derivative.forward;

import java.time.LocalDateTime;
import java.util.Set;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface FutureDelivery<D> {

    @Nonnull
    Set<D> delivery();
    
    boolean contains(LocalDateTime time);

}
