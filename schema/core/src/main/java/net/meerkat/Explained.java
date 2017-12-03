package net.meerkat;

import java.util.function.UnaryOperator;

/**
 *
 * @author ollie
 */
public class Explained<T> implements Explainable {

    private final T value;
    private final ExplanationBuilder explanation;

    public Explained(final T value, final UnaryOperator<ExplanationBuilder> consumer) {
        this(value, consumer.apply(value == null ? new ExplanationBuilder() : new ExplanationBuilder(value.getClass())));
    }

    public Explained(final T value, final ExplanationBuilder explanation) {
        this.value = value;
        this.explanation = explanation.putIfAbsent("value", value);
    }

    public T value() {
        return value;
    }

    @Override
    public ExplanationBuilder explain() {
        return explanation;
    }

}
