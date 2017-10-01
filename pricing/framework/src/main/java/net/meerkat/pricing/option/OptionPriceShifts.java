package net.meerkat.pricing.option;

import net.meerkat.pricing.shifts.InstrumentPriceShifts;
import net.meerkat.pricing.shifts.fx.ExchangeRateShifts;

/**
 *
 * @author Ollie
 */
public interface OptionPriceShifts extends ExchangeRateShifts {

    default InstrumentPriceShifts underlyingShifts() {
        return this;
    }

    static OptionPriceShifts cast(final InstrumentPriceShifts shifts) {
        throw new UnsupportedOperationException(); //TODO
    }

    static OptionPriceShifts none() {
        throw new UnsupportedOperationException(); //TODO
    }

}
