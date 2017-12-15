package net.meerkat.risk.position.exception;

import net.meerkat.risk.position.PositionId;

/**
 *
 * @author Ollie
 */
public class UnknownPositionException extends PositionException {

    private static final long serialVersionUID = 1L;

    public UnknownPositionException(final PositionId positionId) {
        super("Unknown position: " + positionId);
    }

}
