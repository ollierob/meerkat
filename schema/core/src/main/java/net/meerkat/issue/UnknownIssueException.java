package net.meerkat.issue;

import net.meerkat.identifier.instrument.HasInstrumentId;
import net.meerkat.identifier.instrument.InstrumentId;

/**
 *
 * @author ollie
 */
public class UnknownIssueException extends RuntimeException implements HasInstrumentId {

    private static final long serialVersionUID = 1L;

    private final InstrumentId instrumentId;

    public UnknownIssueException(final InstrumentId issueId) {
        this.instrumentId = issueId;
    }

    @Override
    public InstrumentId instrumentId() {
        return instrumentId;
    }

}
