package net.meerkat.functions.optionals;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * @author Ollie
 */
public abstract class Either<L, R> {

    @SuppressWarnings("unchecked")
    public static <L, R> Either<L, R> neither() {
        return Neither.INSTANCE;
    }

    public static <L, R> Either<L, R> left(final L value) {
        return value == null ? neither() : new Left<>(value);
    }

    public static <L, R> Either<L, R> right(final R value) {
        return value == null ? neither() : new Right<>(value);
    }

    public static <L, R> Either<L, R> either(final Supplier<? extends L> getLeft, final Supplier<? extends R> getRight) {
        final L left = getLeft.get();
        return left == null ? right(getRight.get()) : left(left);
    }

    private Either() {
    }

    public Optional<R> right() {
        return Optional.empty();
    }

    public Optional<L> left() {
        return Optional.empty();
    }

    public void consume(final Consumer<? super L> ifLeft, final Consumer<? super R> ifRight) {
    }

    public <L2, R2> Either<L2, R2> map(final Function<? super L, ? extends L2> ifLeft, final Function<? super R, ? extends R2> ifRight) {
        return neither();
    }

    private static final class Neither<L, R> extends Either<L, R> {

        @SuppressWarnings("rawtypes")
        static final Neither INSTANCE = new Neither();

    }

    private static final class Left<L, R> extends Either<L, R> {

        private final L value;

        Left(final L value) {
            this.value = requireNonNull(value);
        }

        @Override
        public Optional<L> left() {
            return Optional.of(value);
        }

        @Override
        public void consume(final Consumer<? super L> ifLeft, final Consumer<? super R> ifRight) {
            ifLeft.accept(value);
        }

        @Override
        public <L2, R2> Either<L2, R2> map(final Function<? super L, ? extends L2> ifLeft, final Function<? super R, ? extends R2> ifRight) {
            return left(ifLeft.apply(value));
        }

    }

    private static final class Right<L, R> extends Either<L, R> {

        private final R value;

        Right(final R value) {
            this.value = requireNonNull(value);
        }

        @Override
        public Optional<R> right() {
            return Optional.of(value);
        }

        @Override
        public void consume(Consumer<? super L> ifLeft, Consumer<? super R> ifRight) {
            ifRight.accept(value);
        }

        @Override
        public <L2, R2> Either<L2, R2> map(final Function<? super L, ? extends L2> ifLeft, final Function<? super R, ? extends R2> ifRight) {
            return right(ifRight.apply(value));
        }

    }

}
