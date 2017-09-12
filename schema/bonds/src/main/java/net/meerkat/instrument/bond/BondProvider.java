package net.meerkat.instrument.bond;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.instrument.InstrumentProvider;

/**
 *
 * @author ollie
 */
public interface BondProvider extends InstrumentProvider<Bond> {

    @Override
    default Bond require(final InstrumentId id) throws UnknownBondException {
        return this.require(id, UnknownBondException::new);
    }

}
