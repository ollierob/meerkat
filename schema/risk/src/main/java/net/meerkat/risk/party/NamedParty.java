package net.meerkat.risk.party;

import net.meerkat.Named;

import javax.annotation.Nonnull;

public class NamedParty extends Named implements Party {

    private final PartyId partyId;

    public NamedParty(final String name, final PartyId partyId) {
        super(name);
        this.partyId = partyId;
    }

    @Nonnull
    @Override
    public PartyId partyId() {
        return partyId;
    }

}
