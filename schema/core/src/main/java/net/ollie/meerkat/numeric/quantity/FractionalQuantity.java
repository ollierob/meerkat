package net.ollie.meerkat.numeric.quantity;

import java.math.BigDecimal;
import java.math.MathContext;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author ollie
 */
public class FractionalQuantity extends AbstractQuantity {

    public static final FractionalQuantity ZERO = new FractionalQuantity(DecimalQuantity.ZERO, DecimalQuantity.ONE);

    public static FractionalQuantity of(final Quantity quantity) {
        return quantity instanceof FractionalQuantity
                ? (FractionalQuantity) quantity
                : new FractionalQuantity(quantity.toDecimalQuantity(), DecimalQuantity.ONE);
    }

    public static FractionalQuantity of(final Quantity numerator, final Quantity denominator) {
        return numerator.isZero()
                ? ZERO
                : new FractionalQuantity(numerator.toDecimalQuantity(), denominator.toDecimalQuantity());
    }

    @XmlAttribute(name = "numerator")
    private DecimalQuantity numerator;

    @XmlAttribute(name = "denominator")
    private DecimalQuantity denominator;

    @Deprecated
    FractionalQuantity() {
    }

    public FractionalQuantity(final DecimalQuantity numerator, final DecimalQuantity denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public FractionalQuantity plus(final Quantity that) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FractionalQuantity times(final Number that) {
        return of(numerator.times(that), denominator);
    }

    public FractionalQuantity over(final Number that) {
        return of(numerator, denominator.times(that));
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

}
