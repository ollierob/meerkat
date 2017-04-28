package net.meerkat.instrument.derivative.forward;

import java.time.LocalDate;
import java.time.YearMonth;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.temporal.date.interim.CompleteInterval;
import net.ollie.goat.temporal.date.interim.Interim;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class YearMonthFutureDeliveryDates implements FutureDeliveryDates {

    @XmlElement(name = "yearMonth")
    private YearMonth yearMonth;

    @Deprecated
    YearMonthFutureDeliveryDates() {
    }

    public YearMonthFutureDeliveryDates(final YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    @Override
    public LocalDate earliest() {
        return yearMonth.atDay(1);
    }

    @Override
    public LocalDate latest() {
        return yearMonth.atEndOfMonth();
    }

    @Override
    public CompleteInterval dates() {
        return Interim.allOf(yearMonth);
    }

}
