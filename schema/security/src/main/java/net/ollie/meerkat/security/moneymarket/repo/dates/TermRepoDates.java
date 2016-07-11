package net.ollie.meerkat.security.moneymarket.repo.dates;

import java.time.LocalDate;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.security.Termed;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class TermRepoDates extends AbstractRepoDates implements Termed {

    @XmlAttribute(name = "far")
    private LocalDate far;

    public TermRepoDates(final LocalDate deal, final LocalDate near, final LocalDate far) {
        super(deal, near);
        this.far = far;
    }

    @Override
    public Optional<LocalDate> far() {
        return Optional.of(far);
    }

    @Override
    @Deprecated
    public LocalDate matures() {
        return far;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public boolean isTerm() {
        return !this.isOvernight();
    }

    @Override
    public String toString() {
        return "Term: deal [" + this.deal()
                + "] near [" + this.near()
                + "] far [" + far
                + "]";
    }

}
