package net.meerkat.identifier.instrument;

/**
 * Stock Exchange Daily Official List.
 *
 * @author ollie
 * @see <a href="https://en.wikipedia.org/wiki/SEDOL">SEDOL</a>
 */
public record Sedol(String value) implements Nsin, HasCheckDigit {

    @Override
    public char checkDigit() {
        return this.lastChar();
    }

    @Override
    public char computeCheckDigit() {
        final char[] sedol = this.value().toCharArray();
        int sum = 0;
        for (int i = 0; i < sedol.length - 1; i++) {
            sum += CHECK_WEIGHTS[i] * checkValue(sedol[i]);
        }
        final int expected = (10 - (sum % 10)) % 10;
        return Character.forDigit(expected, 10);
    }

    private static final int[] CHECK_WEIGHTS = {1, 3, 1, 7, 3, 9};

    private static int checkValue(final char c) {
        return (int) Character.toUpperCase(c) - 55;
    }

    @Override
    public String isinPart() {
        return "00" + this.value();
    }

}
