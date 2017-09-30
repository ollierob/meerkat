package net.meerkat.sensitivity.bond;

import java.util.Map;

import net.meerkat.calculate.sensitivity.yield.DollarDuration;

/**
 *
 * @author Ollie
 */
public class ScaledBondSensitivities implements BondSensitivities {

    private final BondSensitivities unit;
    private final long quantity;

    protected ScaledBondSensitivities(final BondSensitivities unit, final long quantity) {
        this.unit = unit;
        this.quantity = quantity;
    }

    @Override
    public DollarDuration dollarDuration() {
        return unit.dollarDuration().times(quantity);
    }

    @Override
    public ScaledBondSensitivities times(final long quantity) {
        return new ScaledBondSensitivities(unit, quantity);
    }

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("unit sensitivities", unit)
                .put("quantity", quantity);
    }

}
