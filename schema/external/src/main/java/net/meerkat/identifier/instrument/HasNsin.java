package net.meerkat.identifier.instrument;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.identifier.instrument.HasInstrumentIds;


/**
 *
 * @author Ollie
 */
public interface HasNsin extends HasInstrumentIds {

    @Nonnull
    default Optional<Nsin> nsin() {
        return this.instrumentId(Nsin.class);
    }

    @Nonnull
    default <N extends Nsin> Optional<N> nsin(final Class<N> clazz) {
        return this.nsin().flatMap(nsin -> nsin.cast(clazz));
    }

}
