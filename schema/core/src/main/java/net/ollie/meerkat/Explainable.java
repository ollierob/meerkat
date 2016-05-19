package net.ollie.meerkat;

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

    class ExplanationBuilder extends AbstractMap<String, Object> implements Explainable {

        private final Map<String, Object> explanation = new HashMap<>();

        @Override
        public ExplanationBuilder put(final String key, final Object value) {
            final Object newValue;
            if (value instanceof Explainable) {
                newValue = ((Explainable) value).explain();
            } else if (value instanceof Optional) {
                newValue = ((Optional<?>) value).orElse(null);
            } else {
                newValue = value;
            }
            explanation.put(key, newValue);
            return this;
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
