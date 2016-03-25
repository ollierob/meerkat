package net.ollie.meerkat.utils;

/**
 *
 * @author Ollie
 */
public interface SelfTyped<S extends SelfTyped<S>> {

    @SuppressWarnings("unchecked")
    default S self() {
        return (S) this;
    }

}
