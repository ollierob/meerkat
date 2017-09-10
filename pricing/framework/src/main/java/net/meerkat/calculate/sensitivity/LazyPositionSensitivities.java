package net.meerkat.calculate.sensitivity;

import java.util.Optional;

import net.coljate.list.ImmutableList;
import net.coljate.list.List;
import net.coljate.map.Map;
import net.coljate.map.MutableMap;
import net.coljate.set.Set;
import net.meerkat.position.Position;

/**
 *
 * @author ollie
 */
public class LazyPositionSensitivities implements PositionSensitivities {

    private final ImmutableList<PositionSensitivities> sensitivities;
    private final MutableMap<Class<?>, Optional<? extends Sensitivity<?>>> cachedValues = Map.create(4);

    public LazyPositionSensitivities(final List<PositionSensitivities> sensitivities) {
        this.sensitivities = sensitivities.immutableCopy();
    }

    @Override
    public <S extends Sensitivity<S>> Optional<S> get(final Class<S> sensitivity) {
        return (Optional<S>) cachedValues.computeIfAbsent(sensitivity, k -> this.compute((Class) k));
    }

    private <S extends Sensitivity<S>> Optional<S> compute(final Class<S> sensitivity) {
        S aggregate = null;
        for (final PositionSensitivities sensitivities : this.sensitivities) {
            final Optional<S> value = sensitivities.get(sensitivity);
            if (value.isPresent()) {
                aggregate = aggregate == null ? value.get() : aggregate.plus(value.get());
            }
        }
        return Optional.ofNullable(aggregate);
    }

    @Override
    public Set<? extends Position> positions() {
        return sensitivities.serialStream()
                .flatMap(s -> s.positions().serialStream())
                .collect(Set.collector());
    }

    @Override
    public PositionSensitivities plus(final PositionSensitivities that) {
        return new LazyPositionSensitivities(sensitivities.suffixed(that));
    }

}
