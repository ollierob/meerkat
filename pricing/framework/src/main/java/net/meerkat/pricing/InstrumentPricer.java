package net.meerkat.pricing;

import net.meerkat.instrument.Instrument;

/**
 *
 * @author ollie
 */
public interface InstrumentPricer<T, I extends Instrument> extends Pricer<T, I> {

}
