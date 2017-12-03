package net.meerkat.objects.algorithm;

/**
 * @author ollie
 */
public class LuhnAlgorithm {

    public static char check(final String string) {
        final int l = string.length();
        int sum = 0;
        for (int i = 0; i < l; i++) {
            final int c = Character.getNumericValue(string.charAt(i));
            sum += i % 2 == 0 ? sumDigits(2 * c) : c;
        }
        final int mod = (sum * 9) % 10;
        return Character.forDigit(mod, 10);
    }

    private static int sumDigits(final int c) {
        return c >= 10 ? c - 9 : c;
    }

}
