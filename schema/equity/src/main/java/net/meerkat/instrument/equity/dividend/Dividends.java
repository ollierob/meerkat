package net.meerkat.instrument.equity.dividend;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.time.calendar.TemporalSequence;

public interface Dividends<C extends CurrencyId> extends HasCurrencyId, TemporalSequence<Dividend<C>> {

}
