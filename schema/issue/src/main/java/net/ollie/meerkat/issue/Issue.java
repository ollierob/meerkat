package net.ollie.meerkat.issue;

import javax.xml.bind.annotation.XmlElement;

import net.ollie.meerkat.issue.rating.CreditRating;

/**
 *
 * @author ollie
 */
public class Issue {

    @XmlElement(name = "rating")
    private CreditRating rating;

}
