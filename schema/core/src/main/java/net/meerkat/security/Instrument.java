package net.meerkat.security;

import java.security.Security;

/**
 *
 * @author Ollie
 * @see Security
 */
public interface Instrument extends HasInstrument {

    @Override
    default Instrument instrument() {
        return this;
    }

}
