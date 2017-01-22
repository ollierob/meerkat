package net.meerkat.security.interest.repo.dates;

import java.time.LocalDate;
import java.util.Optional;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class OpenRepoDates extends AbstractRepoDates {

    @Deprecated
    OpenRepoDates() {
    }

    public OpenRepoDates(final LocalDate deal, final LocalDate near) {
        super(deal, near);
    }

    @Override
    public Optional<LocalDate> far() {
        return Optional.empty();
    }

}
