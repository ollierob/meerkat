package net.ollie.meerkat.identifier.security;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class SecurityIds implements Externalizable {

    public static SecurityIds singleton(final SecurityId id) {
        return new SecurityIds(Collections.singleton(id));
    }

    @XmlElementRef(name = "id", required = true)
    private Set<SecurityId> ids;

    @Deprecated
    SecurityIds() {
    }

    public SecurityIds(final Set<SecurityId> ids) {
        this.ids = ids;
    }

    public boolean contains(final SecurityId id) {
        return ids.contains(id);
    }

    @Nonnull
    public <S extends SecurityId> Optional<S> id(final Class<S> clazz) {
        return ids.stream()
                .map(id -> id.cast(clazz))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny();
    }

    public boolean isEmpty() {
        return ids.isEmpty();
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(ids);
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        ids = (Set) in.readObject();
    }

}
