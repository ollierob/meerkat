package net.ollie.meerkat.numeric.quantity;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import net.ollie.meerkat.utils.numeric.Numeric;
import net.ollie.meerkat.utils.xml.ExtendingXmlAdapter;
import static net.ollie.meerkat.utils.numeric.Numbers.toBigDecimal;

/**
 *
 * @author Ollie
 */
public interface Quantity extends Numeric.Summable<Quantity> {

    Quantity ZERO = DecimalQuantity.ZERO;

    @Override
    default Quantity plus(final Quantity number) {
        return DecimalQuantity.valueOf(this.decimalValue().add(number.decimalValue()));
    }

    @Override
    default Quantity times(final Number number) {
        return DecimalQuantity.valueOf(this.decimalValue().multiply(toBigDecimal(number)));
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

    XmlAdapter<DecimalQuantity, Quantity> XML_ADAPTER = ExtendingXmlAdapter.of(Quantity::toDecimalQuantity);

}
