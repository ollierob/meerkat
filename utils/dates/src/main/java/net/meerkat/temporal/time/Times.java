package net.meerkat.temporal.time;

import net.meerkat.functions.Functions;

import java.time.Instant;

/**
 * @author Ollie
 */
public class Times {

    protected Times() {
    }

    public static boolean equals(final Instant left, final Instant right) {
        return Functions.ifBothNonNull(left, right, Instant::equals);
    }

}
