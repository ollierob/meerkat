package net.ollie.meerkat.calculate.price.bond.future;

import java.time.temporal.Temporal;
import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.identifier.security.SecurityIds;

/**
 *
 * @author Ollie
 */
public interface CheapestToDeliverProvider<T extends Temporal> {

    @Nonnull
    Optional<CheapestToDeliver<?>> get(T temporal, SecurityIds bondFutureId);

}
