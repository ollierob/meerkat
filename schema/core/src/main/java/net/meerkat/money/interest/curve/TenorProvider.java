package net.meerkat.money.interest.curve;

import net.meerkat.temporal.date.Periods;
import net.ollie.goat.data.Element;
import net.ollie.goat.data.Provider;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.time.Period;

/**
 *
 * @author ollie
 */
public interface TenorProvider<T extends Tenor> extends Provider<String, T> {

    @Override
    default Element<T> getElement(final String name) {
        return Element.ofNullable(this.get(name));
    }

    @Override
    T get(String name);

    @Override
    @Nonnull
    default T require(final String name) {
        final T tenor = this.get(name);
        if (tenor == null) {
            throw new InvalidTenorException(name);
        } else {
            return tenor;
        }
    }

    @CheckForNull
    T get(Period period);

    @Nonnull
    default T require(final Period period) {
        final T tenor = this.get(period);
        if (tenor == null) {
            throw new InvalidTenorException(period.toString());
        } else {
            return tenor;
        }
    }

    @Nonnull
    default T spot() {
        return this.require(Period.ZERO);
    }

    @Nonnull
    default T threeMonths() {
        return this.require(Periods.THREE_MONTHS);
    }

    @Nonnull
    default T oneYear() {
        return this.require(Periods.ONE_YEAR);
    }

    class InvalidTenorException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public InvalidTenorException(final String tenor) {
            super(tenor);
        }

    }

}
