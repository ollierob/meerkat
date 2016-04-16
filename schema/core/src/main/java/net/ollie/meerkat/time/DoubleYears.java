package net.ollie.meerkat.time;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import net.ollie.goat.date.Dates;
import net.ollie.meerkat.utils.time.Years;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class DoubleYears implements Years {

    private static final long serialVersionUID = 1L;

    @XmlValue
    private double value;

    @Deprecated
    DoubleYears() {
    }

    public DoubleYears(double value) {
        this.value = value;
    }

    @Override
    public LocalDate addTo(final LocalDate date) {
        return date.plusDays(Math.round(value / Dates.DAYS_PER_YEAR));
    }

    @Override
    public DoubleYears plus(final Years that) {
        return new DoubleYears(value + that.doubleValue());
    }

    @Override
    public Years times(final Number that, final RoundingMode rounding) {
        return new DoubleYears(value * that.doubleValue());
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return BigDecimal.valueOf(value).round(context);
    }

}
