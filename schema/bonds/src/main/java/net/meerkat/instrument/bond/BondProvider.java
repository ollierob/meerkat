package net.meerkat.instrument.bond;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.instrument.InstrumentProvider;
import net.meerkat.instrument.bond.exception.InvalidBondException;
import net.meerkat.instrument.bond.exception.UnknownBondException;

/**
 *
 * @author ollie
 */
public interface BondProvider extends InstrumentProvider<Bond> {

    @Override
    Bond get(InstrumentId key) throws InvalidBondException;

    @Override
    default Bond require(final InstrumentId id) throws UnknownBondException, InvalidBondException {
        return this.require(id, UnknownBondException::new);
    }

}
