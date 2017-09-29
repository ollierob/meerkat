package net.meerkat.identifier.instrument;

import java.util.Optional;

import net.meerkat.identifier.instrument.HasInstrumentIds;

/**
 *
 * @author Ollie
 */
public interface HasOptionSymbol extends HasInstrumentIds {

    default Optional<OptionSymbol> optionSymbol() {
        return this.instrumentId(OptionSymbol.class);
    }

}
