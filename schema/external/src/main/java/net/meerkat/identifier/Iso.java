package net.meerkat.identifier;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface Iso {

    @Nonnull
    String value();

    default char first() {
        return this.value().charAt(0);
    }

    String standard();

}
