package net.meerkat.security;

/**
 *
 * @author Ollie
 */
public interface Security extends HasSecurity {

    @Override
    default Security security() {
        return this;
    }

}
