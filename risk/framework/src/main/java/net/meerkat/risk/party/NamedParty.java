package net.meerkat.risk.party;

import net.meerkat.objects.HasName;

public record NamedParty(PartyId partyId, String name) implements Party, HasName {

}
