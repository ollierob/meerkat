package net.ollie.meerkat.calculate.var;

import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.numeric.Percentage;

/**
 *
 * @author Ollie
 */
public class HistoricVar implements Var {

    private final Percentage percentile;
    private final Money value;

    public HistoricVar(final Percentage percentile, final Money value) {
        this.percentile = percentile;
        this.value = value;
    }

    public Percentage percentile() {
        return percentile;
    }

    @Override
    public Money atRisk() {
        return value;
    }

}
