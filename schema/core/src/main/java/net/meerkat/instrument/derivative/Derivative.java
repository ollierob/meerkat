package net.meerkat.instrument.derivative;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.InstrumentSnapshot;
import net.meerkat.instrument.Security;

import javax.annotation.Nonnull;

/**
 * @author ollie
 */
public interface Derivative<I extends Instrument> extends Security {

    @Nonnull
    InstrumentIds underlyingId();

    @Nonnull
    default I underlying(final InstrumentSnapshot<? extends I> snapshot) {
        return snapshot.require(this.underlyingId());
    }

    @Nonnull
    default Instrument ultimateUnderlying(final InstrumentSnapshot<Instrument> snapshot) {
        Instrument self = this;
        while (true) {
            Instrument underlying = self instanceof Derivative
                    ? ((Derivative) self).underlying(snapshot)
                    : null;
            if (underlying == null || underlying == self) {
                return self;
            }
            self = underlying;
        }
    }

}
