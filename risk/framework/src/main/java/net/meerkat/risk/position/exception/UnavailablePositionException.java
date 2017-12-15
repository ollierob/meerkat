package net.meerkat.risk.position.exception;

public class UnavailablePositionException extends PositionException {

    public UnavailablePositionException(final Object key) {
        super("Positions not available for " + key);
    }

}
