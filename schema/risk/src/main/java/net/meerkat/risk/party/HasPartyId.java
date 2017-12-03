package net.meerkat.risk.party;

import net.coljate.set.Set;

import javax.annotation.Nonnull;

public interface HasPartyId extends HasPartyIds {

    @Nonnull
    PartyId partyId();

    @Nonnull
    @Override
    default Set<? extends PartyId> partyIds() {
        return Set.of(this.partyId());
    }

}
