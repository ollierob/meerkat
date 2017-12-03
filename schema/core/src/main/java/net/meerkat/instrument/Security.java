package net.meerkat.instrument;

import net.meerkat.issuer.HasIssuerId;

/**
 * A tradeable financial asset that has been {@link net.meerkat.issuer.IssuerId issued} by some entity.
 *
 * @author ollie
 * @see Instrument
 */
public interface Security extends Instrument, HasIssuerId {

    @Override
    @Deprecated
    default Security instrument() {
        return this;
    }

}
