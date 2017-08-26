package net.meerkat.instrument;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.issue.HasIssue;
import net.meerkat.issue.Issue;

/**
 *
 * @author ollie
 */
public class IssuedSecurity
        extends NamedInstrument
        implements Security, HasIssue {

    private static final long serialVersionUID = 1L;

    private final Issue issue;

    public IssuedSecurity(final String name, final InstrumentIds identifiers, final Issue issue) {
        super(name, identifiers);
        this.issue = issue;
    }

    @Override
    public Issue issue() {
        return issue;
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("issue", issue);
    }

}
