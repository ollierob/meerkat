package net.ollie.meerkat.issuer;

import java.util.List;

import javax.annotation.CheckForNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.hierarchy.Hierarchy;
import net.ollie.goat.collections.Lists;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class IssuerHierarchy implements Hierarchy<Issuer> {

    @XmlElement(name = "issuer")
    private Issuer issuer;

    @XmlElement(name = "parent")
    private List<Issuer> hierarchy;

    @Override
    public Issuer root() {
        return issuer;
    }

    @CheckForNull
    public Issuer parent() {
        return Lists.first(hierarchy);
    }

    @CheckForNull
    public Issuer ultimateParent() {
        return Lists.last(hierarchy);
    }

}
