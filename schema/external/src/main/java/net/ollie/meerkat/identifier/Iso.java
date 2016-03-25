package net.ollie.meerkat.identifier;

/**
 *
 * @author ollie
 */
public interface Iso {

    String value();

    default char first() {
        return this.value().charAt(0);
    }

    String standard();

}
