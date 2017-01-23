package net.meerkat.utils.comparators;

/**
 *
 * @author Ollie
 */
public class Comparators {

    private Comparators() {
    }

    public static <T extends Comparable<? super T>> T max(final T left, final T right) {
        return left.compareTo(right) >= 0 ? left : right;
    }

    public static <T extends Comparable<? super T>> T min(final T left, final T right) {
        return left.compareTo(right) <= 0 ? left : right;
    }

    public static <T extends Comparable<? super T>> T bounded(final T left, final T mid, final T right) {
        if (min(left, mid) == mid) {
            return left;
        }
        if (max(mid, right) == mid) {
            return right;
        }
        return mid;
    }

}
