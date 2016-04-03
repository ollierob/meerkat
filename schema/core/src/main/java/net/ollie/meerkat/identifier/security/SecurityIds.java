package net.ollie.meerkat.identifier.security;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.utils.Classes;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class SecurityIds {

    public static SecurityIds singleton(final SecurityId id) {
        return new SecurityIds(Collections.singleton(id));
    }

    @XmlElementRef(name = "id")
    private Set<SecurityId> ids;

    @Deprecated
    SecurityIds() {
    }

    public SecurityIds(final Set<SecurityId> ids) {
        this.ids = ids;
    }

    @Nonnull
    public <S extends SecurityId> Optional<S> id(final Class<S> clazz) {
        return ids.stream()
                .map(id -> Classes.cast(id, clazz))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny();
    }

}
