package net.meerkat.pricing.option;

import net.meerkat.pricing.shifts.InstrumentPriceShifts;

/**
 *
 * @author Ollie
 */
public interface OptionPriceShifts extends InstrumentPriceShifts {

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
