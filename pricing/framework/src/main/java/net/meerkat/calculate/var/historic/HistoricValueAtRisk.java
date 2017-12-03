package net.meerkat.calculate.var.historic;

import net.meerkat.calculate.var.ValueAtRisk;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.numeric.percentage.Percentage;

/**
 *
 * @author Ollie
 */
public class HistoricValueAtRisk<C extends CurrencyId> implements ValueAtRisk {

    private final Percentage percentile;
    private final Money<C> atRisk;

    public HistoricValueAtRisk(final Percentage percentile, final Money<C> atRisk) {
        this.percentile = percentile;
        this.atRisk = atRisk;
    }

    public Percentage percentile() {
        return percentile;
    }

    @Override
    public Money<C> atRisk() {
        return atRisk;
    }

}
