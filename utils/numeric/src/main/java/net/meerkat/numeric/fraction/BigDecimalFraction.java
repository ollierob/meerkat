package net.meerkat.numeric.fraction;

import net.meerkat.numeric.BigDecimals;
import net.meerkat.numeric.Numeric;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author Ollie
 */
@XmlRootElement
public class BigDecimalFraction
        extends Number
        implements Numeric.Summable<BigDecimalFraction>, Externalizable {

    private static final long serialVersionUID = 1L;

    public static final BigDecimalFraction MINUS_ONE = BigDecimalFraction.of(-1, 1);
    public static final BigDecimalFraction ZERO = new BigDecimalFraction(BigDecimal.ZERO, BigDecimal.ONE);

    public static BigDecimalFraction of(final long numerator, final long denominator) {
        return of(BigDecimal.valueOf(numerator), BigDecimal.valueOf(denominator));
    }

    public static BigDecimalFraction of(final Number numerator, final Number denominator) {
        if (numerator instanceof BigDecimalFraction) {
            return ((BigDecimalFraction) numerator).over(denominator);
        }
        if (denominator instanceof BigDecimalFraction) {
            return ((BigDecimalFraction) denominator).reciprocal().times(numerator);
        }
        return of(BigDecimals.toBigDecimal(numerator), BigDecimals.toBigDecimal(denominator));
    }

    public static BigDecimalFraction of(final BigDecimal numerator, final BigDecimal denominator) {
        if (numerator.signum() == 0) {
            return ZERO;
        }
        return denominator.signum() < 0
                ? new BigDecimalFraction(numerator.negate(), denominator.negate())
                : new BigDecimalFraction(numerator, denominator);
    }

    public static BigDecimalFraction of(final Number number) {
        return number instanceof BigDecimalFraction
                ? (BigDecimalFraction) number
                : of(BigDecimals.toBigDecimal(number));
    }

    public static BigDecimalFraction of(final BigDecimal d) {
        return new BigDecimalFraction(d, BigDecimal.ONE);
    }

    @XmlAttribute(name = "numerator")
    private BigDecimal numerator;

    @XmlAttribute(name = "denominator")
    private BigDecimal denominator;

    @Deprecated
    BigDecimalFraction() {
    }

    BigDecimalFraction(final BigDecimal numerator, final BigDecimal denominator) {
        if (denominator.signum() == 0) {
            throw new ArithmeticException(numerator + "/" + denominator);
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Nonnull
    public BigDecimal numerator() {
        return numerator;
    }

    @Nonnull
    public BigDecimal denominator() {
        return denominator;
    }

    @Override
    public boolean isZero() {
        return numerator.signum() == 0;
    }

    @Override
    public boolean isNegative() {
        return !this.isZero()
                && numerator.signum() != denominator.signum();
    }

    @Override
    public boolean isPositive() {
        return !this.isZero()
                && numerator.signum() == denominator.signum();
    }

    public BigDecimalFraction plus(@Nonnull final Number number) {
        return number instanceof BigDecimalFraction
                ? this.plus((BigDecimalFraction) number)
                : this.plus(BigDecimals.toBigDecimal(number));
    }

    @CheckReturnValue
    public BigDecimalFraction plus(@Nonnull final BigDecimal bd) {
        return of(numerator.add(bd.multiply(denominator)), denominator);
    }

    @Override
    public BigDecimalFraction plus(final BigDecimalFraction that) {
        return of(
                this.numerator.multiply(that.denominator).add(that.numerator.multiply(this.denominator)),
                this.denominator.multiply(that.denominator));
    }

    @Override
    public BigDecimalFraction minus(final BigDecimalFraction that) {
        return of(
                this.numerator.multiply(that.denominator).subtract(that.numerator.multiply(this.denominator)),
                this.denominator.multiply(that.denominator));
    }

    public BigDecimalFraction minus(final int that) {
        return BigDecimalFraction.of(numerator.subtract(denominator), denominator);
    }

    @Override
    public BigDecimalFraction negate() {
        return of(numerator.negate(), denominator);
    }

    @Override
    @Deprecated
    public BigDecimalFraction times(final Number that, final RoundingMode rounding) {
        return this.times(that);
    }

    @Override
    public BigDecimalFraction times(final Number number) {
        return number instanceof BigDecimalFraction
                ? this.times((BigDecimalFraction) number)
                : this.times(BigDecimals.toBigDecimal(number));
    }

    public BigDecimalFraction times(final BigDecimal decimal) {
        return of(numerator.multiply(decimal), denominator);
    }

    public BigDecimalFraction times(final BigDecimalFraction that) {
        return of(numerator.multiply(that.numerator), denominator.multiply(that.denominator));
    }

    public BigDecimalFraction over(final Number number) {
        return number instanceof BigDecimalFraction
                ? this.over((BigDecimalFraction) number)
                : this.over(BigDecimals.toBigDecimal(number));
    }

    public BigDecimalFraction over(final BigDecimal decimal) {
        return of(numerator, denominator.multiply(decimal));
    }

    public BigDecimalFraction over(final BigDecimalFraction that) {
        return this.times(that.reciprocal());
    }

    public BigDecimalFraction abs() {
        return this.isNegative()
                ? of(numerator.abs(), denominator.abs())
                : this;
    }

    @Override
    public BigDecimalFraction reciprocal() {
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

    public boolean isReduced() {
        final BigInteger n = numerator.unscaledValue();
        final BigInteger d = denominator.unscaledValue();
        final BigInteger gcd = n.gcd(d);
        return gcd.compareTo(BigInteger.ONE) == 0;
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
        return obj instanceof BigDecimalFraction
                && this.valuesEqual((BigDecimalFraction) obj);
    }

    @Override
    public boolean valuesEqual(final BigDecimalFraction that) {
        return this.minus(that).isZero();
    }

    @Override
    public int compareTo(final BigDecimalFraction that) {
        final BigDecimal n1 = this.numerator.multiply(that.denominator);
        final BigDecimal n2 = that.numerator.multiply(this.denominator);
        return n1.compareTo(n2);
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(numerator);
        out.writeObject(denominator);
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        numerator = (BigDecimal) in.readObject();
        denominator = (BigDecimal) in.readObject();
    }

}
