package net.meerkat.temporal.date.interim;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Optional;

/**
 * @author ollie
 */
public interface Interim {

    boolean contains(LocalDate date);

    @Nonnull
    Optional<CompleteInterval> closed();

    static CompleteInterval allOf(final YearMonth yearMonth) {
        return new CompleteInterval(yearMonth.atDay(1), yearMonth.atEndOfMonth());
    }

}
