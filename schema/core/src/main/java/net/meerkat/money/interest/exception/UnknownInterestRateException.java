package net.meerkat.money.interest.exception;

import net.meerkat.money.interest.HasInterestRateId;
import net.meerkat.money.interest.InterestRateId;

/**
 *
 * @author Ollie
 */
public class UnknownInterestRateException extends InterestRateException implements HasInterestRateId {

    private static final long serialVersionUID = 1L;

    private final InterestRateId id;

    public UnknownInterestRateException(final InterestRateId id) {
        super("Unknown rate: " + id);
        this.id = id;
    }

    @Override
    public InterestRateId interestRateId() {
        return id;
    }

}
