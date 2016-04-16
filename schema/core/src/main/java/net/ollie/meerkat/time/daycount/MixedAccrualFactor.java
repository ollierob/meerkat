package net.ollie.meerkat.time.daycount;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.utils.time.Years;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class MixedAccrualFactor implements AccrualFactor {

    @XmlElementRef(name = "day_count")
    private DayCount dayCount;

    @XmlElementRef(name = "year_count")
    private YearCount yearCount;

    @Deprecated
    MixedAccrualFactor() {
    }

    public MixedAccrualFactor(DayCount dayCount, YearCount yearCount) {
        this.dayCount = dayCount;
        this.yearCount = yearCount;
    }

    @Override
    public int daysBetween(LocalDate startInclusive, LocalDate endExclusive) {
        return dayCount.daysBetween(startInclusive, endExclusive);
    }

    @Override
    public Years yearsBetween(LocalDate startInclusive, LocalDate endExclusive) {
        return yearCount.yearsBetween(startInclusive, endExclusive);
    }

}
