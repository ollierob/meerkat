package net.ollie.meerkat.numeric;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.xml.bind.annotation.XmlAttribute;

import net.ollie.meerkat.utils.Accumulator;
import net.ollie.meerkat.utils.numeric.Numbers;
import net.ollie.meerkat.utils.numeric.Numeric;

/**
 *
 * @author Ollie
 */
public class DecimalFraction
        extends Number
        implements Numeric.Summable<DecimalFraction>, Externalizable {

    private static final long serialVersionUID = 1L;

    public static final DecimalFraction MINUS_ONE = DecimalFraction.of(-1, 1);
    public static final DecimalFraction ZERO = new DecimalFraction(BigDecimal.ZERO, BigDecimal.ONE);

    public static Accumulator.Homogeneous<DecimalFraction> accumulator() {
        return DecimalFraction::plus;
    }

    public static DecimalFraction of(final Number numerator, final Number denominator) {
        if (numerator instanceof DecimalFraction) {
            return ((DecimalFraction) numerator).over(denominator);
        }
        if (denominator instanceof DecimalFraction) {
            return ((DecimalFraction) denominator).inverse().times(numerator);
        }
        return of(Numbers.toBigDecimal(numerator), Numbers.toBigDecimal(denominator));
    }

    public static DecimalFraction of(final BigDecimal numerator, final BigDecimal denominator) {
        if (numerator.signum() == 0) {
            return ZERO;
        }
        return denominator.signum() < 0
                ? new DecimalFraction(numerator.negate(), denominator.negate())
                : new DecimalFraction(numerator, denominator);
    }

    @XmlAttribute(name = "numerator")
    private BigDecimal numerator;

    @XmlAttribute(name = "denominator")
    private BigDecimal denominator;

    @Deprecated
    DecimalFraction() {
    }

    DecimalFraction(final BigDecimal numerator, final BigDecimal denominator) {
        if (denominator.signum() == 0) {
            throw new ArithmeticException(numerator + "/" + denominator);
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public BigDecimal numerator() {
        return numerator;
    }

    public BigDecimal denominator() {
        return denominator;
    }

    @Override
    public boolean isZero() {
        return numerator.signum() == 0;
    }

    public DecimalFraction plus(final BigDecimal bd) {
        return of(numerator.add(bd.multiply(denominator)), denominator);
    }

    @Override
    public DecimalFraction plus(final DecimalFraction that) {
        return of(
                this.numerator.multiply(that.denominator).add(that.numerator.multiply(this.denominator)),
                this.denominator.multiply(that.denominator));
    }

    @Override
    public DecimalFraction negate() {
        return new DecimalFraction(numerator.negate(), denominator);
    }

    @Override
    @Deprecated
    public DecimalFraction times(final Number that, final RoundingMode rounding) {
        return this.times(that);
    }

    public DecimalFraction times(final Number number) {
        return number instanceof DecimalFraction
                ? this.times((DecimalFraction) number)
                : this.times(Numbers.toBigDecimal(number));
    }

    public DecimalFraction times(final BigDecimal decimal) {
        return of(numerator.multiply(decimal), denominator);
    }

    public DecimalFraction times(final DecimalFraction that) {
        return of(numerator.multiply(that.numerator), denominator.multiply(that.denominator));
    }

    public DecimalFraction over(final Number number) {
        return number instanceof DecimalFraction
                ? this.over((DecimalFraction) number)
                : this.over(Numbers.toBigDecimal(number));
    }

    public DecimalFraction over(final BigDecimal decimal) {
        return of(numerator, denominator.multiply(decimal));
    }

    public DecimalFraction over(final DecimalFraction that) {
        return this.times(that.inverse());
    }

    public DecimalFraction inverse() {
        return of(denominator, numerator);
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return numerator.divide(denominator, context);
    }

    @Override
    public int intValue() {
        return this.decimalValue(MathContext.DECIMAL32).intValue();
    }

    @Override
    public long longValue() {
        return this.decimalValue(MathContext.DECIMAL32).longValue();
    }

    @Override
    public float floatValue() {
        return this.decimalValue(MathContext.DECIMAL32).floatValue();
    }

    @Override
    public double doubleValue() {
        return this.decimalValue(MathContext.DECIMAL64).doubleValue();
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(this.doubleValue());
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof DecimalFraction
                && this.equals((DecimalFraction) obj);
    }

    public boolean equals(final DecimalFraction that) {
        return this.minus(that).isZero();
    }

    @Override
    public int compareTo(final DecimalFraction that) {
        final BigDecimal n1 = this.numerator.multiply(that.denominator);
        final BigDecimal n2 = that.numerator.multiply(this.denominator);
        return n1.compareTo(n2);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(numerator);
        out.writeObject(denominator);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        numerator = (BigDecimal) in.readObject();
        denominator = (BigDecimal) in.readObject();
    }

}
