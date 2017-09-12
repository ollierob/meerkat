package net.meerkat.instrument.equity;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.instrument.InstrumentProvider;

/**
 *
 * @author Ollie
 */
public interface EquityProvider extends InstrumentProvider<Equity> {

    @Override
    default Equity require(final InstrumentId id) throws UnknownEquityException {
        return this.require(id, UnknownEquityException::new);
    }

}
