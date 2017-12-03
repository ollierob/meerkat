package net.meerkat.risk.party.exception;

import java.util.Arrays;

public class UnknownPartyException extends PartyException {

    public UnknownPartyException(final Object... keys) {
        super("Unknown party for " + Arrays.toString(keys));
    }

}
