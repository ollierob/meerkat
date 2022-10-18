package net.meerkat.instrument.equity.pair;

import net.meerkat.identifier.instrument.HasInstrumentId;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.equity.Equity;
import net.meerkat.instrument.equity.EquityDerivative;

import javax.annotation.Nonnull;
import java.util.Set;

public interface EquityPair extends EquityDerivative<Equity>, InstrumentDefinition {

    @Nonnull
    Set<Leg> buyLegs();

    @Nonnull
    Set<Leg> sellLegs();

    @Nonnull
    LegRelationship relationship();

    default boolean isMulti() {
        return this.buyLegs().size() > 1 || this.sellLegs().size() > 1;
    }

    record Leg(InstrumentId instrumentId) implements HasInstrumentId {

    }

    @Override
    default <R> R handleWith(final EquityDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
