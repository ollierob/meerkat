package net.ollie.goat.temporal.date.interim;

import javax.xml.bind.annotation.XmlAttribute;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Optional;

/**
 * @author ollie
 */
public class MonthYearInterval implements Interim {

    @XmlAttribute(name = "yearMonth")
    private YearMonth yearMonth;

    @Override
    public boolean contains(final LocalDate date) {
        return yearMonth.getYear() == date.getYear()
                && yearMonth.getMonthValue() == date.getMonthValue();
    }

    @Override
    public Optional<CompleteInterval> closed() {
        return Optional.of(new CompleteInterval(yearMonth.atDay(1), yearMonth.atEndOfMonth()));
    }

}
