package net.ollie.meerkat.numeric.quantity;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author Ollie
 */
public class IntegerQuantity extends AbstractQuantity implements Quantity {

    public static final IntegerQuantity ZERO = new IntegerQuantity(0);

    public static IntegerQuantity valueOf(final int value) {
        return value == 0
                ? ZERO
                : new IntegerQuantity(value);
    }

    private final int value;

    IntegerQuantity(final int value) {
        this.value = value;
    }

    public int intValue() {
        return value;
    }

    @Override
    public boolean isZero() {
        return value == 0;
    }

    @Override
    public Quantity plus(final Quantity that) {
        return that instanceof IntegerQuantity
                ? this.plus((IntegerQuantity) that)
                : Quantity.super.plus(that);
    }

    public IntegerQuantity plus(final IntegerQuantity that) {
        return valueOf(value + that.intValue());
    }

    @Override
    public Quantity times(final Number that) {
        return that instanceof Integer
                ? this.times((Integer) that)
                : Quantity.super.times(that);
    }

    public IntegerQuantity times(final int that) {
        return valueOf(value * that);
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return BigDecimal.valueOf(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
