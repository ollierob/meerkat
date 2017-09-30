package net.meerkat.money.interest.exception;

import java.util.Arrays;

/**
 *
 * @author ollie
 */
public class UnavailableInterestRateException extends InterestRateException {

    private static final long serialVersionUID = 1L;

    public UnavailableInterestRateException(final Object... keys) {
        super("Interest rates unavailable for " + Arrays.toString(keys) + "!");
    }

}
