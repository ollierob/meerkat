package net.meerkat;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Explainable {

    @Nonnull
    Map<String, Object> explain();

    default ExplanationBuilder explanationBuilder() {
        return new ExplanationBuilder(this.getClass());
    }

    default ExplanationBuilder explanationBuilder(final Map<String, ?> map) {
        if (map instanceof ExplanationBuilder) {
            return (ExplanationBuilder) map;
        }
        final ExplanationBuilder explanation = this.explanationBuilder();
        explanation.putAll(map);
        return explanation;
    }

    class ExplanationBuilder extends AbstractMap<String, Object> implements Explainable {

        private final Map<String, Object> explanation = new HashMap<>();

        public ExplanationBuilder(final Class<?> clazz) {
            explanation.put("class", clazz);
        }

        @Override
        public ExplanationBuilder put(final String key, final Object value) {
            final Object newValue;
            if (value instanceof Explainable) {
                newValue = ((Explainable) value).explain();
            } else if (value instanceof Optional) {
                newValue = ((Optional<?>) value).orElse(null);
            } else {
                newValue = value == null ? null : value.toString();
            }
            explanation.put(key, newValue);
            return this;
        }

        public ExplanationBuilder putIfNonNull(final String key, final Object value) {
            return this.putIf(value != null, key, value);
        }

        public ExplanationBuilder putIf(final boolean predicate, final String key, final Object value) {
            return predicate ? this.put(key, value) : this;
        }

        @Override
        public Map<String, Object> explain() {
            return Collections.unmodifiableMap(explanation);
        }

        @Override
        public Set<Entry<String, Object>> entrySet() {
            return explanation.entrySet();
        }

    }

}
