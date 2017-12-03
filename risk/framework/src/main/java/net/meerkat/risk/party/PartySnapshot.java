package net.meerkat.risk.party;

import net.meerkat.risk.party.exception.UnknownPartyException;
import net.ollie.goat.data.Provider;

import javax.annotation.Nonnull;

public interface PartySnapshot<P extends Party> extends Provider<PartyId, P> {

    @Nonnull
    @Override
    default P require(final PartyId key) throws UnknownPartyException {
        return this.require(key, UnknownPartyException::new);
    }
}
