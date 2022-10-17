package net.meerkat.identifier.instrument;

import net.meerkat.identifier.Iso;

/**
 * Financial Instrument Short Name.
 *
 * @author Ollie
 */
public record Fisn(String issuerName, String instrumentDescription) implements Iso, InstrumentId {

    @Override
    public String value() {
        return issuerName + '/' + instrumentDescription;
    }

    @Override
    public String standard() {
        return "18774";
    }

}
