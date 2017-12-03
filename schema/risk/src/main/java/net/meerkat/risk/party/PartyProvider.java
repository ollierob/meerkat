package net.meerkat.risk.party;

import net.meerkat.risk.party.exception.PartyException;
import net.meerkat.risk.party.exception.UnavailablePartySnapshotException;
import net.ollie.goat.data.CompositeProvider;

import javax.annotation.Nonnull;

public interface PartyProvider<T, P extends Party> extends CompositeProvider<T, PartyId, P, PartySnapshot<P>> {

    @Nonnull
    @Override
    default PartySnapshot<P> require(final T key) throws UnavailablePartySnapshotException {
        return this.require(key, UnavailablePartySnapshotException::new);
    }

    @Nonnull
    @Override
    default P require(final T t, final PartyId partyId) throws PartyException {
        return CompositeProvider.super.require(t, partyId);
    }

}
