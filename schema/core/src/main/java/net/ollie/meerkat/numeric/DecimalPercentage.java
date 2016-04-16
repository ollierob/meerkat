package net.ollie.meerkat.numeric;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import net.ollie.meerkat.utils.Accumulator;
import net.ollie.meerkat.utils.numeric.Numbers;
import net.ollie.meerkat.utils.numeric.Numeric;
import net.ollie.meerkat.utils.numeric.Percentage;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class DecimalPercentage
        extends Percentage
        implements Externalizable {

    private static final long serialVersionUID = 1L;
    public static final Percentage ZERO_PERCENT = new DecimalPercentage(BigDecimal.ZERO);
    public static final Percentage ONE_PERCENT = new DecimalPercentage(BigDecimal.ONE);
    public static final Percentage ONE_HUNDRED_PERCENT = new DecimalPercentage(Numbers.ONE_HUNDRED);

    public static Percentage basisPoints(final int amount) {
        return new DecimalPercentage(BigDecimal.valueOf(amount).movePointLeft(2));
    }

    public static Accumulator.Homogeneous<Percentage> accumulator() {
        return Percentage::plus;
    }

    @XmlValue
    private BigDecimal value;

    @Deprecated
    DecimalPercentage() {
    }

    public DecimalPercentage(final int value) {
        this(BigDecimal.valueOf(value));
    }

    public DecimalPercentage(final double value) {
        this(BigDecimal.valueOf(value));
    }

    public DecimalPercentage(final BigDecimal value) {
        this.value = value;
    }

    @Override
    public Percentage plus(final Percentage that) {
        return new DecimalPercentage(value.add(that.decimalValue()));
    }

    @Override
    public Percentage times(final Number that, final RoundingMode rounding) {
        return this.times(that);
    }

    public Percentage times(final Number that) {
        return new DecimalPercentage(value.multiply(Numbers.toBigDecimal(that)));
    }

    public Percentage timesBy(final Numeric<?> that) {
        return this.times(that.decimalValue());
    }

//    public <T extends Numeric<T>> T times(final T that, final RoundingMode rounding) {
//        return that.times(this.doubleValue(), rounding);
//    }
    @Override
    public BigDecimal decimalValue() {
        return value.movePointLeft(2);
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return this.decimalValue().round(context);
    }

    @Override
    public double doubleValue() {
        return this.decimalValue().doubleValue();
    }

    @Override
    public int intValue() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public long longValue() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public float floatValue() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public boolean isNegative() {
        return value.signum() < 0;
    }

    @Override
    public Percentage inverse() {
        return ONE_HUNDRED_PERCENT.minus(this);
    }

    @Override
    public String toString() {
        return value + "%";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Percentage
                && this.valuesEqual((Percentage) obj);
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(value);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        value = (BigDecimal) in.readObject();
    }

}
