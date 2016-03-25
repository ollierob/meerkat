package net.ollie.meerkat.numeric;

import java.math.BigDecimal;
import java.math.MathContext;

import javax.xml.bind.annotation.XmlAttribute;

import net.ollie.meerkat.utils.numeric.Numbers;

/**
 *
 * @author Ollie
 */
public class DecimalFraction extends Number {

    private static final long serialVersionUID = 1L;

    public static final DecimalFraction ZERO = new DecimalFraction(BigDecimal.ZERO, BigDecimal.ONE);

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
        return numerator.signum() == 0
                ? ZERO
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
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public BigDecimal numerator() {
        return numerator;
    }

    public BigDecimal denominator() {
        return denominator;
    }

    public DecimalFraction negate() {
        return new DecimalFraction(numerator.negate(), denominator);
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
        return of(numerator.multiply(that.denominator), denominator.multiply(that.numerator));
    }

    public DecimalFraction inverse() {
        return of(denominator, numerator);
    }

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
        return this.decimalValue(MathContext.DECIMAL64).intValue();
    }

}
