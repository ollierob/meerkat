package net.meerkat.calculate.var.historic;

import net.meerkat.money.Money;
import net.ollie.goat.numeric.percentage.Percentage;
import net.meerkat.calculate.var.ValueAtRisk;
import net.meerkat.identifier.currency.CurrencyId;

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
