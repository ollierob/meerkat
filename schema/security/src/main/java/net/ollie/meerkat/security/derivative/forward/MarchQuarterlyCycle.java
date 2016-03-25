package net.ollie.meerkat.security.derivative.forward;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class MarchQuarterlyCycle implements FutureDelivery<Month> {

    private static final Set<Month> MONTHS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            Month.MARCH,
            Month.JUNE,
            Month.SEPTEMBER,
            Month.DECEMBER)));

    @Override
    public Set<Month> delivery() {
        return MONTHS;
    }

    @Override
    public boolean contains(final LocalDateTime time) {
        return MONTHS.contains(time.getMonth());
    }

}
