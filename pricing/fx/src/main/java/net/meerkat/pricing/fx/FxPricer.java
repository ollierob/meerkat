package net.meerkat.pricing.fx;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.fx.FxInstrument;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.InstrumentPricer;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;

public interface FxPricer<T, F extends FxInstrument<?, ?>> extends InstrumentPricer<T, F> {

    @Override
    <C extends CurrencyId> FxPrice.Shiftable<C> price(
            T temporal,
            F instrument,
            C currency,
            InstrumentPriceShifts shifts)
            throws InstrumentPriceException;

}
