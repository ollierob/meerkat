package net.meerkat.instrument;

import net.meerkat.Explainable;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;

/**
 *
 * @author Ollie
 */
public class IdentifiedInstrument implements Instrument, Explainable {

    private final InstrumentIds instrumentIds;

    public IdentifiedInstrument(final InstrumentId instrumentId) {
        this(InstrumentIds.of(instrumentId));
    }

    public IdentifiedInstrument(final InstrumentIds instrumentIds) {
        this.instrumentIds = instrumentIds;
    }

    @Override
    public InstrumentIds instrumentIds() {
        return instrumentIds;
    }

    @Override
    public ExplanationBuilder explain() {
        return this.explanationBuilder().put("ids", instrumentIds);
    }

    @Override
    public String toString() {
        return this.explain().toString();
    }

}
