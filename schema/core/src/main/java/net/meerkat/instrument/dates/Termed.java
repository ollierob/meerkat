package net.meerkat.instrument.dates;

import net.meerkat.instrument.dates.Issued;

import java.time.Period;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface Termed extends Issued, Matures {

    @Nonnull
    default Period term() {
        return Period.between(this.issueDate(), this.maturityDate());
    }

}
