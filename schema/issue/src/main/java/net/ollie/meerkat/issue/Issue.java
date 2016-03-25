package net.ollie.meerkat.issue;

import net.ollie.meerkat.issue.rating.IssueRating;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author ollie
 */
public class Issue {

    @XmlElement(name = "rating")
    private IssueRating rating;

}
