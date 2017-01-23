package net.meerkat.security;

/**
 *
 * @author Ollie
 */
public interface Instrument extends HasInstrument {

    @Override
    default Instrument instrument() {
        return this;
    }

}
