package net.meerkat.identifier.instrument.option;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import net.meerkat.identifier.instrument.InstrumentId;

/**
 *
 * @author ollie
 * @see <a href="https://en.wikipedia.org/wiki/Option_symbol">Option symbol</a>
 */
public class OptionSymbol implements InstrumentId {

    private final String root;
    private final LocalDate expiration;
    private final boolean isPut;
    private final BigDecimal strike;

    public OptionSymbol(String root, LocalDate expiration, boolean isPut, BigDecimal strike) {
        this.root = root;
        this.expiration = expiration;
        this.isPut = isPut;
        this.strike = strike;
    }

    public String toString(final Function<OptionSymbol, String> toString) {
        return toString.apply(this);
    }

    public String toOccString() {
        return this.toString(OptionSymbol::toOccSymbol);
    }

    @Override
    public String toString() {
        return this.toOccString();
    }

    private static final DateTimeFormatter YYMMDD = DateTimeFormatter.ofPattern("yyMMdd");
    private static final DecimalFormat SIGFIGS = new DecimalFormat("00000000");

    public static String toOccSymbol(final OptionSymbol symbol) {
        return String.format("%1$-6s", symbol.root)
                + YYMMDD.format(symbol.expiration)
                + (symbol.isPut ? 'P' : 'C')
                + SIGFIGS.format(symbol.strike.scaleByPowerOfTen(3));
    }

}
