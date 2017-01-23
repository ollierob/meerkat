package net.meerkat.numeric.quantity;

import java.io.Serializable;
import java.math.RoundingMode;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import net.ollie.goat.numeric.BigDecimals;
import net.ollie.goat.numeric.Numeric;
import net.meerkat.utils.Accumulator;
import net.meerkat.utils.xml.ExtendingXmlAdapter;

/**
 *
 * @author Ollie
 */
public interface Quantity
        extends Numeric.Summable<Quantity>, Serializable {

    Quantity ZERO = DecimalQuantity.ZERO;

    @Override
    default Quantity plus(final Quantity number) {
        return DecimalQuantity.valueOf(this.decimalValue().add(number.decimalValue()));
    }

    @Override
    default Quantity times(final Number number) {
        return DecimalQuantity.valueOf(this.decimalValue().multiply(BigDecimals.toBigDecimal(number)));
    }

    @Override
    @Deprecated
    default Quantity times(final Number number, final RoundingMode rounding) {
        return this.times(number);
    }

    @Nonnull
    default DecimalQuantity toDecimalQuantity() {
        return DecimalQuantity.valueOf(this.decimalValue());
    }

    static boolean valuesEqual(@Nonnull final Quantity left, @Nonnull final Quantity right) {
        return left.decimalValue().compareTo(right.decimalValue()) == 0;
    }

    static int valueHash(@Nonnull final Quantity quantity) {
        return quantity.decimalValue().hashCode();
    }

    Accumulator.Homogeneous<Quantity> ACCUMULATOR = Quantity::plus;
    XmlAdapter<DecimalQuantity, Quantity> XML_ADAPTER = ExtendingXmlAdapter.of(Quantity::toDecimalQuantity);

}
