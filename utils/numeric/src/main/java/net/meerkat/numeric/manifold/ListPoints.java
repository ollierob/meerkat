package net.meerkat.numeric.manifold;

import net.coljate.list.List;

import java.util.Iterator;

class ListPoints<T> implements Points<T> {

    private final List<? extends T> list;

    public ListPoints(List<? extends T> list) {
        this.list = list;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
