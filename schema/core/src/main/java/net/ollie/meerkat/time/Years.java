package net.ollie.meerkat.time;

import java.time.LocalDate;

/**
 *
 * @author ollie
 */
public interface Years extends Comparable<Years> {

    double value();
    
    LocalDate addTo(LocalDate date);

    @Override
    default int compareTo(Years that) {
        return Double.compare(this.value(), that.value());
    }

}
