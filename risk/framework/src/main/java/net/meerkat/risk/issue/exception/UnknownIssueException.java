package net.meerkat.risk.issue.exception;

import net.meerkat.identifier.instrument.HasInstrumentId;
import net.meerkat.identifier.instrument.InstrumentId;

/**
 *
 * @author ollie
 */
public class UnknownIssueException extends IssueException implements HasInstrumentId {

    private static final long serialVersionUID = 1L;

    private final InstrumentId instrumentId;

    public UnknownIssueException(final InstrumentId instrumentId) {
        super("Unknown issue for " + instrumentId);
        this.instrumentId = instrumentId;
    }

    @Override
    public InstrumentId instrumentId() {
        return instrumentId;
    }

}
