package net.meerkat.numeric.quantity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.MathContext;
import static java.util.Objects.requireNonNull;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import net.ollie.goat.numeric.BigDecimals;
import static net.ollie.goat.numeric.BigDecimals.toBigDecimal;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class DecimalQuantity
        extends AbstractQuantity
        implements Externalizable {

    private static final long serialVersionUID = 1L;

    public static final DecimalQuantity ZERO = new DecimalQuantity(BigDecimal.ZERO);
    public static final DecimalQuantity ONE = new DecimalQuantity(BigDecimal.ONE);

    public static DecimalQuantity valueOf(final Number number) {
        return valueOf(BigDecimals.toBigDecimal(number));
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
    public DecimalQuantity over(final Number that, final MathContext context) {
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(value);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        value = (BigDecimal) in.readObject();
    }

}
