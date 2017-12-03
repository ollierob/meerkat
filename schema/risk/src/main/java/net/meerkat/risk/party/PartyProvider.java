package net.meerkat.risk.party;

import net.ollie.goat.data.CompositeProvider;

public interface PartyProvider<T, P extends Party> extends CompositeProvider<T, PartyId, P, PartySnapshot<P>> {

}
