package net.ollie.meerkat.time;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.math3.fraction.Fraction;

import net.ollie.meerkat.utils.time.Years;

/**
 *
 * @author Ollie
 */
public class FractionalYears implements Years {

    private static final long serialVersionUID = 1L;

    public static final FractionalYears ONE = of(1, 1);

    public static FractionalYears of(final int numerator, final int denominator) {
        return new FractionalYears(new Fraction(numerator, denominator));
    }

    @XmlElement(name = "years")
    private Fraction fraction;

    @Deprecated
    FractionalYears() {
    }

    public FractionalYears(final Fraction years) {
        this.fraction = years;
    }

    @Override
    public LocalDate addTo(LocalDate date) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public Years plus(final Years that) {
        return that instanceof FractionalYears
                ? this.plus((FractionalYears) that)
                : new DoubleYears(this.fraction.doubleValue() + that.doubleValue());
    }

    public FractionalYears plus(final FractionalYears that) {
        return new FractionalYears(this.fraction.add(that.fraction));
    }

    @Override
    public Years times(Number that, RoundingMode rounding) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return BigDecimal.valueOf(fraction.doubleValue()).round(context);
    }

}
