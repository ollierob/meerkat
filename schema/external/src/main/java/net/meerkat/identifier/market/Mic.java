package net.meerkat.identifier.market;

import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.StringWrapper;
import net.meerkat.identifier.Iso;
import net.ollie.meerkat.identifier.market.MarketId;

/**
 * Market Identifier Code.
 *
 * @author ollie
 * @see <a href="https://en.wikipedia.org/wiki/Market_Identifier_Code">MIC</a>
 */
@XmlRootElement
public class Mic
        extends StringWrapper
        implements Iso, MarketId {

    private static final long serialVersionUID = 1L;

    @Deprecated
    Mic() {
    }

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
