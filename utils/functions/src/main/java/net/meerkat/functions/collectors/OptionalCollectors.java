package net.meerkat.functions.collectors;

import net.meerkat.functions.optionals.Optionals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author Ollie
 */
public final class OptionalCollectors {

    private OptionalCollectors() {
    }

    public static <T> Collector<Optional<T>, ?, Optional<T>> fromOptional() {
        return java.util.stream.Collector.<Optional<T>, List<Optional<T>>, Optional<T>>of(
                ArrayList::new,
                List::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                },
                list -> Optionals.zeroOrOne(list).orElse(Optional.empty())
        );
    }

    public static <T> Collector<T, ?, Optional<T>> intoOptional() {
        return java.util.stream.Collector.<T, List<T>, Optional<T>>of(
                ArrayList::new,
                List::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                },
                list -> Optionals.zeroOrOne(list));
    }

    public static <T> Collector<T, ?, Optional<T>> intoOptional(final Supplier<T> defaultValue) {
        return Collector.<T, List<T>, Optional<T>>of(ArrayList::new, List::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                },
                list -> Optionals.zeroOrOne(list, defaultValue));
    }

    public static <T> Collector<Optional<T>, ?, Optional<T>> oneOrEmpty() {
        return Collector.<Optional<T>, OnlyContainer<T>, Optional<T>>of(
                OnlyContainer::new,
                OnlyContainer::add,
                OnlyContainer::or, OnlyContainer::value);
    }

    private static class OnlyContainer<T> {

        private int presentCount;
        private Optional<T> optional;

        OnlyContainer() {
            this(0, Optional.empty());
        }

        OnlyContainer(final int presentCount, final Optional<T> optional) {
            this.presentCount = presentCount;
            this.optional = optional;
        }

        void add(final Optional<T> optional) {
            if (optional.isPresent()) {
                presentCount++;
                this.optional = optional;
            }
        }

        OnlyContainer<T> or(final OnlyContainer<T> that) {
            this.presentCount += that.presentCount;
            this.optional = that.optional.isPresent() ? that.optional : this.optional;
            return this;
        }

        Optional<T> value() {
            return presentCount <= 1
                    ? optional
                    : Optional.empty();
        }

    }

}
