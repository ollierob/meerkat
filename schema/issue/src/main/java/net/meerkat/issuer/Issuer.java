package net.meerkat.issuer;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.organization.Organization;
import net.meerkat.rating.CreditRating;
import net.meerkat.rating.HasCreditRating;

/**
 *
 * @author ollie
 */
public class Issuer implements HasCreditRating {

    @XmlElementRef(name = "organization", required = false)
    private Organization organization;

    @XmlElementRef(name = "rating")
    private CreditRating rating;

    @Nonnull
    public Organization organization() {
        return organization;
    }

    @Override
    public CreditRating creditRating() {
        return rating;
    }

}
