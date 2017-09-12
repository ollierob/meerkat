package net.meerkat.identifier.market;

import net.meerkat.StringWrapper;
import net.meerkat.identifier.Iso;

/**
 * Market Identifier Code.
 *
 * @author ollie
 * @see <a href="https://en.wikipedia.org/wiki/Market_Identifier_Code">MIC</a>
 */
public class Mic
        extends StringWrapper
        implements Iso, MarketId {

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
