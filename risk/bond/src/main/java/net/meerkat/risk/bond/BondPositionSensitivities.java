package net.meerkat.risk.bond;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;

import net.meerkat.calculate.sensitivity.yield.DollarDuration;
import net.meerkat.risk.sensitivities.PositionSensitivities;
import net.meerkat.sensitivity.bond.BondInstrumentSensitivities;
import net.meerkat.sensitivity.bond.BondSensitivities;

/**
 *
 * @author Ollie
 * @see BondPosition
 */
public class BondPositionSensitivities implements BondSensitivities, PositionSensitivities {

    private final BondInstrumentSensitivities unit;
    private final BondPosition position;

    public BondPositionSensitivities(@Nonnull final BondInstrumentSensitivities unit, final BondPosition position) {
        this.unit = Objects.requireNonNull(unit, "sensitivities");
        this.position = position;
    }

    @Override
    public DollarDuration dollarDuration() {
        return unit.dollarDuration().times(position.quantity());
    }

    @Override
    public BondInstrumentSensitivities instrumentSensitivities() {
        return unit;
    }

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("sensitivities", unit)
                .put("position", position);
    }

}
