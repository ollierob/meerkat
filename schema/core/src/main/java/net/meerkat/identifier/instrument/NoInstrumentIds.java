package net.meerkat.identifier.instrument;

import net.coljate.set.Set;

import javax.annotation.Nonnull;

class NoInstrumentIds implements InstrumentIds {

    static final NoInstrumentIds INSTANCE = new NoInstrumentIds();

    @Nonnull
    @Override
    public Set<? extends InstrumentId> values() {
        return Set.of();
    }
}
