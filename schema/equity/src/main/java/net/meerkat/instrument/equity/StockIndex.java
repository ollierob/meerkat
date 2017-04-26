package net.meerkat.instrument.equity;

import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class StockIndex extends NamedInstrument {

    @Deprecated
    StockIndex() {
    }

    public StockIndex(final String name, final InstrumentIds identifiers) {
        super(name, identifiers);
    }

}
