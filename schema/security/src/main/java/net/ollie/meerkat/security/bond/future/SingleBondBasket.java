package net.ollie.meerkat.security.bond.future;

import java.util.Collections;
import java.util.Set;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.security.bond.Bond;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class SingleBondBasket implements BondBasket {

    @XmlElementRef(name = "bond")
    private Bond bond;

    @Deprecated
    SingleBondBasket() {
    }

    public SingleBondBasket(final Bond bond) {
        this.bond = bond;
    }

    @Override
    public Set<Bond> bonds() {
        return Collections.singleton(bond);
    }

    @Override
    public String name() {
        return bond.name();
    }

    @Override
    public boolean contains(final Bond bond) {
        return this.bond.equals(bond);
    }

}
