package net.meerkat.risk.party;

import net.coljate.set.Set;

import javax.annotation.Nonnull;

public interface HasCounterpartyId extends HasPartyId {

    @Nonnull
    @Override
    PartyId partyId();

    @Nonnull
    PartyId counterpartyId();

    @Nonnull
    @Override
    default Set<? extends PartyId> partyIds() {
        return Set.of(this.partyId(), this.counterpartyId());
    }

}
