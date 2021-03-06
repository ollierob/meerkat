package net.meerkat.instrument.equity;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.instrument.InstrumentSnapshot;
import net.meerkat.instrument.equity.exception.InvalidEquityException;
import net.meerkat.instrument.equity.exception.UnknownEquityException;

/**
 *
 * @author Ollie
 */
public interface EquitySnapshot extends InstrumentSnapshot<Equity> {

    @Override
    Equity get(InstrumentId key) throws InvalidEquityException;

    @Override
    default Equity require(final InstrumentId id) throws InvalidEquityException, UnknownEquityException {
        return this.require(id, UnknownEquityException::new);
    }

}
