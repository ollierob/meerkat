package net.meerkat.numeric.quantity;

/**
 *
 * @author Ollie
 */
public abstract class AbstractQuantity implements Quantity {

    @Override
    public abstract String toString();

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Quantity
                && Quantity.valuesEqual(this, (Quantity) obj);
    }

    @Override
    public int hashCode() {
        return Quantity.valueHash(this);
    }

}
