package net.meerkat.instrument.equity.dividend;

import net.meerkat.identifier.instrument.InstrumentId;
import net.ollie.goat.data.CompositeProvider;

public interface DividendProvider<T> extends CompositeProvider<T, InstrumentId, Dividends, DividendSnapshot> {

}
