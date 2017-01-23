package net.meerkat.instrument;

import java.time.Period;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface Termed extends Issued, Matures {

    @Nonnull
    default Period term() {
        return Period.between(this.issued(), this.matures());
    }

}
