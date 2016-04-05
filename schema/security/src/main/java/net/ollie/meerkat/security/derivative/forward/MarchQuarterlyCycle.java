package net.ollie.meerkat.security.derivative.forward;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Any business day in March, June, September or December.
 *
 * @author ollie
 */
@XmlRootElement
public class MarchQuarterlyCycle implements FutureDelivery {

    private static final Set<Month> MONTHS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            Month.MARCH,
            Month.JUNE,
            Month.SEPTEMBER,
            Month.DECEMBER)));

    public Set<Month> deliveryMonths() {
        return MONTHS;
    }

    @Override
    public boolean contains(final LocalDate date) {
        return MONTHS.contains(date.getMonth());
    }

}
