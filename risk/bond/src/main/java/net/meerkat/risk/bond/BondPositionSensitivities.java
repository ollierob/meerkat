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
    private final long quantity;

    public BondPositionSensitivities(@Nonnull final BondInstrumentSensitivities unit, final long quantity) {
        this.unit = Objects.requireNonNull(unit, "sensitivities");
        this.quantity = quantity;
    }

    @Override
    public DollarDuration dollarDuration() {
        return unit.dollarDuration().times(quantity);
    }

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("unit sensitivities", unit)
                .put("quantity", quantity);
    }

}
