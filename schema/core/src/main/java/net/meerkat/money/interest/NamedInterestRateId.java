package net.meerkat.money.interest;

import net.meerkat.Named;

/**
 *
 * @author ollie
 */
public class NamedInterestRateId
        extends Named
        implements InterestRateId {

    private static final long serialVersionUID = 1L;

    @Deprecated
    NamedInterestRateId() {
    }

    public NamedInterestRateId(final String value) {
        super(value);
    }

}
