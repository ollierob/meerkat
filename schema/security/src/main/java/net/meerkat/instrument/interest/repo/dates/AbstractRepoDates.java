package net.meerkat.instrument.interest.repo.dates;

import java.time.LocalDate;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author ollie
 */
public abstract class AbstractRepoDates implements RepoDates {

    @XmlAttribute(name = "deal")
    private LocalDate deal;

    @XmlAttribute(name = "near")
    private LocalDate near;

    @Deprecated
    AbstractRepoDates() {
    }

    protected AbstractRepoDates(final LocalDate deal, final LocalDate near) {
        this.deal = deal;
        this.near = Objects.requireNonNull(near);
    }

    @Override
    public LocalDate deal() {
        return deal;
    }

    @Override
    @Deprecated
    public LocalDate issueDate() {
        return this.deal();
    }

    @Override
    public LocalDate near() {
        return near;
    }

}
