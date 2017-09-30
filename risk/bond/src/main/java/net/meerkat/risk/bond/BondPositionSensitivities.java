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

    private final BondInstrumentSensitivities sensitivities;
    private final BondPosition position;

    public BondPositionSensitivities(@Nonnull final BondInstrumentSensitivities unit, final BondPosition position) {
        this.sensitivities = Objects.requireNonNull(unit, "sensitivities");
        this.position = position;
    }

    @Override
    public DollarDuration dollarDuration() {
        return sensitivities.dollarDuration().times(position.quantity());
    }

    @Override
    public BondPosition position() {
        return position;
    }

    @Override
    public BondInstrumentSensitivities instrumentSensitivities() {
        return sensitivities;
    }

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("sensitivities", sensitivities)
                .put("position", position);
    }

}
