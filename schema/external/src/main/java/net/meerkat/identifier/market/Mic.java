package net.meerkat.identifier.market;

import net.meerkat.StringWrapper;
import net.meerkat.identifier.Iso;

/**
 * Market Identifier Code.
 *
 * @author ollie
 * @see <a href="https://en.wikipedia.org/wiki/Market_Identifier_Code">MIC</a>
 * @see <a href="http://www.iotafinance.com/en/ISO-10383-Market-Identification-Codes-MIC.html">MICs</a>
 */
public class Mic
        extends StringWrapper
        implements Iso, MarketId {

    public static Mic NASDAQ = new Mic("XNAS");
    public static Mic LSE = new Mic("XLON");
    public static Mic BOERSE = new Mic("XBER");

    public Mic(final String value) {
        super(value);
    }

    @Override
    public String value() {
        return super.value();
    }

    @Override
    public String standard() {
        return "10383";
    }

    @Override
    public char first() {
        return super.first();
    }

}
