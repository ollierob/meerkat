package net.ollie.meerkat.identifier.country;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class CountrySubdivisionIso extends CountryIso {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "subdivision")
    private String subdivision;

    @Deprecated
    CountrySubdivisionIso() {
    }

    public CountrySubdivisionIso(final CountryIso country, final String subdivision) {
        super(country.value());
        this.subdivision = subdivision;
    }

    @Override
    public String value() {
        return super.value() + '-' + subdivision;
    }

    @Override
    public String standard() {
        return "3166-2";
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeUTF(subdivision);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        subdivision = in.readUTF();
    }

}
