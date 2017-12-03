package net.ollie.goat.numeric.interpolation;

import net.ollie.goat.numeric.Numeric;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

/**
 * @author ollie
 */
public interface ListInterpolator<V> extends Interpolator<Double, V> {

    V interpolate(double index, List<V> values);

    @Override
    default V interpolate(final Double key, final NavigableMap<Double, V> map) {
        return this.interpolate(key, new ArrayList<>(map.values()));
    }

    static <V extends Numeric.Summable<V>> ListInterpolator<V> linear() {
        return (index, values) -> {
            throw new UnsupportedOperationException(); //TODO
        };
    }

}
