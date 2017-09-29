package net.meerkat.identifier.instrument;

/**
 *
 * @author ollie
 */
public interface HasCheckDigit {

    char checkDigit();

    char computeCheckDigit();

    default boolean isValid() {
        return this.checkDigit() == this.computeCheckDigit();
    }

}
