package net.meerkat.time.calendar;

import java.time.LocalDate;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface Calendar {

    boolean isSupported(@Nonnull LocalDate date);

    class UnsupportedDateException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public UnsupportedDateException(final LocalDate date) {
            super(date + " is not supported by this calendar.");
        }

    }

}
