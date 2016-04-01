package net.ollie.meerkat.utils.collections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ollie
 */
public class Sets {

    @SafeVarargs
    public static <T> Set<T> asUnmodifiableSet(final T... array) {
        return java.util.Collections.unmodifiableSet(new HashSet<>(Arrays.asList(array)));
    }

}
