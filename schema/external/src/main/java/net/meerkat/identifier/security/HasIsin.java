package net.meerkat.identifier.security;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.identifier.instrument.HasInstrumentIds;

/**
 *
 * @author Ollie
 */
public interface HasIsin extends HasNsin, HasInstrumentIds {

    @Nonnull
    default Optional<Isin> isin() {
        return this.instrumentId(Isin.class);
    }

    @Override
    default Optional<Nsin> nsin() {
        return this.isin().flatMap(Isin::nsin);
    }

}
