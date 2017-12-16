package net.meerkat.instrument.equity.dividend;

import net.meerkat.identifier.instrument.InstrumentId;
import net.ollie.goat.data.Provider;

public interface DividendSnapshot extends Provider<InstrumentId, Dividends> {

}
