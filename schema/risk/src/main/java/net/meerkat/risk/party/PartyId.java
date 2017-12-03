package net.meerkat.risk.party;

import javax.annotation.Nonnull;

public interface PartyId extends HasPartyId {

    @Nonnull
    @Override
    @Deprecated
    default PartyId partyId() {
        return this;
    }

}
