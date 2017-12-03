package net.meerkat.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Ollie
 */
public class Arrays {

    private static Map<Class<?>, Function<Object, String>> arraysToString = java.util.Collections.unmodifiableMap(new HashMap<Class<?>, Function<Object, String>>() {

        {
            this.put(Object[].class, array -> java.util.Arrays.toString((Object[]) array));
            this.put(double[].class, array -> java.util.Arrays.toString((double[]) array));
            this.put(long[].class, array -> java.util.Arrays.toString((long[]) array));
            this.put(int[].class, array -> java.util.Arrays.toString((int[]) array));
            this.put(short[].class, array -> java.util.Arrays.toString((short[]) array));
            this.put(byte[].class, array -> java.util.Arrays.toString((byte[]) array));
            this.put(char[].class, array -> java.util.Arrays.toString((char[]) array));
            this.put(boolean[].class, array -> java.util.Arrays.toString((boolean[]) array));
        }

    });

    public static String toString(final Object object) {
        return object == null || !object.getClass().isArray()
                ? String.valueOf(object)
                : arraysToString.get(object.getClass()).apply(object);
    }

}
