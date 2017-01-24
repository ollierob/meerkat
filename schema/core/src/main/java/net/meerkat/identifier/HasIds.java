package net.meerkat.identifier;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElement;

import net.meerkat.utils.Classes.Castable;

/**
 *
 * @author ollie
 */
public abstract class HasIds<T extends Castable> implements Externalizable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "id")
    private Set<T> ids;

    @Deprecated
    protected HasIds() {
    }

    protected HasIds(final Set<T> ids) {
        this.ids = ids;
    }

    public boolean contains(final T id) {
        return ids.contains(id);
    }

    @Nonnull
    public <M extends T> Optional<M> id(final Class<M> clazz) {
        return ids.stream()
                .map(id -> id.cast(clazz))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny();
    }

    public boolean isEmpty() {
        return ids.isEmpty();
    }

    public int size() {
        return ids.size();
    }

    @Nonnull
    public void accept(final Consumer<? super T> consumer) {
        ids.forEach(consumer);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(ids.size());
        for (final T id : ids) {
            out.writeObject(id);
        }
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        final int size = in.readInt();
        ids = new HashSet<>(size);
        for (int i = 0; i < size; i++) {
            ids.add((T) in.readObject());
        }
    }

}
