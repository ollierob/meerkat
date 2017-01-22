package net.meerkat.identifier.security;

import javax.xml.bind.annotation.XmlAttribute;

import net.meerkat.identifier.Iso;

/**
 * Financial Instrument Short Name.
 *
 * @author Ollie
 */
public class Fisn implements Iso, SecurityId {

    @XmlAttribute(name = "issuer")
    private String issuerName;

    @XmlAttribute(name = "instrument")
    private String instrumentDescription;

    @Deprecated
    Fisn() {
    }

    public Fisn(final String issuerName, final String instrumentDescription) {
        this.issuerName = issuerName;
        this.instrumentDescription = instrumentDescription;
    }

    @Override
    public String value() {
        return issuerName + '/' + instrumentDescription;
    }

    @Override
    public String standard() {
        return "18774";
    }

}
