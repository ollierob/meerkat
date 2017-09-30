package net.meerkat.pricing;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.Instrument;
import net.meerkat.pricing.shifts.InstrumentShifts;

/**
 *
 * @author ollie
 */
public interface InstrumentPricer<T, I extends Instrument> extends Pricer<T, I> {

    <C extends CurrencyId> ShiftablePrice<C> price(T temporal, I instrument, C currency, InstrumentShifts shifts) throws InstrumentPriceException;

    @Override
    default <C extends CurrencyId> ShiftablePrice<C> price(final T temporal, final I instrument, final C currency) throws InstrumentPriceException {
        return this.price(temporal, instrument, currency, InstrumentShifts.none());
    }

}
