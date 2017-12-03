package net.meerkat.risk.party.exception;

import java.util.Arrays;

public class UnavailablePartySnapshotException extends PartyException {

    public UnavailablePartySnapshotException(final Object... keys) {
        super("Parties not available for " + Arrays.toString(keys));
    }

}
