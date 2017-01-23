package net.meerkat.instrument;

import net.meerkat.issuer.HasIssuerId;
import net.meerkat.issuer.Issuer;

/**
 * A tradeable financial asset that has been {@link Issuer issued} by some entity.
 *
 * @author ollie
 */
public interface Security extends Instrument, HasIssuerId {

    @Override
    @Deprecated
    default Security instrument() {
        return this;
    }

}
