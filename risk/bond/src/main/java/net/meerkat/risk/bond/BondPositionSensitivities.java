package net.meerkat.risk.bond;

import java.util.Map;

import net.meerkat.calculate.sensitivity.yield.DollarDuration;
import net.meerkat.risk.sensitivities.PositionSensitivities;
import net.meerkat.sensitivity.bond.BondInstrumentSensitivities;
import net.meerkat.sensitivity.bond.BondSensitivities;

/**
 *
 * @author Ollie
 */
public class BondPositionSensitivities implements BondSensitivities, PositionSensitivities {

    private final BondInstrumentSensitivities unit;
    private final long quantity;

    protected BondPositionSensitivities(final BondInstrumentSensitivities unit, final long quantity) {
        this.unit = unit;
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
