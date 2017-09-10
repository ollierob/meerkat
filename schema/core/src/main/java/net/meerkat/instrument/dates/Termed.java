package net.meerkat.instrument.dates;

import java.time.Period;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface Termed extends Issued, Matures {

    @Nonnull
    default Period issueToMaturity() {
        return Period.between(this.issueDate(), this.maturityDate());
    }

}
