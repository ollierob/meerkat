package net.meerkat.money.interest;

/**
 *
 * @author Ollie
 */
public class UnknownInterestRateException extends RuntimeException implements HasInterestRateId {

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
