package net.meerkat.identifier.market;

import net.meerkat.identifier.Iso;

/**
 * Market Identifier Code.
 *
 * @author ollie
 * @see <a href="https://en.wikipedia.org/wiki/Market_Identifier_Code">MIC</a>
 * @see <a href="http://www.iotafinance.com/en/ISO-10383-Market-Identification-Codes-MIC.html">MICs</a>
 */
public record Mic(String value) implements Iso, MarketId {

    @Override
    public String standard() {
        return "10383";
    }

}
