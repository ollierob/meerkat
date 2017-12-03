package net.meerkat.calculate.sensitivity.id;

import net.meerkat.calculate.sensitivity.Sensitivity;
import net.meerkat.functions.suppliers.Suppliers;

import java.util.stream.Collector;

/**
 *
 * @author ollie
 */
public class NonCollectingSensitivityId<S extends Sensitivity> extends CastingSensitivityId<S> {

    public NonCollectingSensitivityId(final String name, final Class<S> clazz) {
        super(name, clazz);
    }

    @Override
    public Collector<S, ?, S> collector() {
        return Collector.<S, SingletonHolder<S>, S>of(
                SingletonHolder::new,
                SingletonHolder::add,
                SingletonHolder::mergeIn,
                SingletonHolder::onlyValue);
    }

    private static class SingletonHolder<T> {

        private T value;
        private int count;

        void add(final T value) {
            this.value = value;
            count++;
        }

        T onlyValue() {
            return count == 1 ? value : null;
        }

        SingletonHolder<T> mergeIn(final SingletonHolder<T> that) {
            this.value = Suppliers.firstNonNull(this.value, that.value);
            this.count += that.count;
            return this;
        }

    }

}
