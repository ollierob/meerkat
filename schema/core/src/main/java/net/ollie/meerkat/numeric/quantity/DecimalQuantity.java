package net.ollie.meerkat.numeric.quantity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import static java.util.Objects.requireNonNull;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import static net.ollie.meerkat.utils.numeric.Numbers.toBigDecimal;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class DecimalQuantity extends AbstractQuantity implements Quantity, Serializable {

    private static final long serialVersionUID = 1L;

    public static final DecimalQuantity ZERO = new DecimalQuantity(BigDecimal.ZERO);
    public static final DecimalQuantity ONE = new DecimalQuantity(BigDecimal.ONE);

    public static DecimalQuantity valueOf(final Number number) {
        return valueOf(toBigDecimal(number));
    }

    public static DecimalQuantity valueOf(@Nonnull final BigDecimal number) {
        return number == BigDecimal.ZERO
                ? ZERO
                : new DecimalQuantity(number);
    }

    @XmlValue
    private BigDecimal value;

    @Deprecated
    DecimalQuantity() {
    }

    DecimalQuantity(final BigDecimal value) {
        this.value = requireNonNull(value);
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return value.round(context);
    }

    @Override
    public Quantity over(final Number that, MathContext context) {
        return valueOf(value.divide(toBigDecimal(that), context));
    }

    @Override
    public DecimalQuantity toDecimalQuantity() {
        return this;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
