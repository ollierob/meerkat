package net.ollie.meerkat.numeric;

import java.math.BigDecimal;
import java.math.MathContext;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import net.ollie.meerkat.utils.numeric.Numbers;
import net.ollie.meerkat.utils.numeric.Numeric;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class Percentage extends Number implements Numeric.Summable<Percentage> {

    public static final Percentage ZERO_PERCENT = new Percentage(BigDecimal.ZERO);
    public static final Percentage ONE_PERCENT = new Percentage(BigDecimal.ONE);

    public static Percentage basisPoints(final int amount) {
        return new Percentage(BigDecimal.valueOf(amount).movePointLeft(2));
    }

    @XmlValue
    private BigDecimal value;

    @Deprecated
    Percentage() {
    }

    public Percentage(final int value) {
        this(BigDecimal.valueOf(value));
    }

    public Percentage(final double value) {
        this(BigDecimal.valueOf(value));
    }

    public Percentage(final BigDecimal value) {
        this.value = value;
    }

    @Override
    public Percentage plus(final Percentage that) {
        return new Percentage(value.add(that.value));
    }

    @Override
    public Percentage times(final Number that) {
        return new Percentage(value.multiply(Numbers.toBigDecimal(that)));
    }

    public <T extends Numeric<T>> T times(final T that) {
        return that.times(this.doubleValue());
    }

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
        return this.decimalValue().intValue();
    }

    @Override
    public long longValue() {
        return this.decimalValue().longValue();
    }

    @Override
    public float floatValue() {
        return this.decimalValue().floatValue();
    }

    public boolean isNegative() {
        return value.signum() < 0;
    }

    @Override
    public String toString() {
        return value + "%";
    }

}
