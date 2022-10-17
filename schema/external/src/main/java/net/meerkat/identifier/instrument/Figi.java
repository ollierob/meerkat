package net.meerkat.identifier.instrument;

/**
 * Fixed Instrument Global Identifier.
 *
 * @author ollie
 * @see <a href="https://en.wikipedia.org/wiki/Financial_Instrument_Global_Identifier">FIGI</a>
 */
public record Figi(String value) implements HasCheckDigit, InstrumentId {

    @Override
    public char checkDigit() {
        return value.charAt(value.length() - 1);
    }

    @Override
    public char computeCheckDigit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
