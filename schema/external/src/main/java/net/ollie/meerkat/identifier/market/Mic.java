package net.ollie.meerkat.identifier.market;

import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.StringWrapper;
import net.ollie.meerkat.identifier.Iso;

/**
 * Market Identifier Code.
 *
 * @author ollie
 * @see <a href="https://en.wikipedia.org/wiki/Market_Identifier_Code">MIC</a>
 */
@XmlRootElement
public class Mic extends StringWrapper implements Iso, MarketId {

    @Deprecated
    Mic() {
    }

    public Mic(final String value) {
        super(value);
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
