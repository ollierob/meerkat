package net.meerkat.instrument;

import net.meerkat.issuer.HasIssuerId;
import net.meerkat.security.Instrument;

/**
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
