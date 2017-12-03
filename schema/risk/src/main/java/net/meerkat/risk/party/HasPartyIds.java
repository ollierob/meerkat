package net.meerkat.risk.party;

import net.coljate.set.Set;

import javax.annotation.Nonnull;

public interface HasPartyIds {

    @Nonnull
    Set<? extends PartyId> partyIds();

}
