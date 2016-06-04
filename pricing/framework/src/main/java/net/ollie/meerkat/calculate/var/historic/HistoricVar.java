package net.ollie.meerkat.calculate.var.historic;

import net.ollie.goat.money.currency.Currency;
import net.ollie.goat.money.Money;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.meerkat.calculate.var.Var;

/**
 *
 * @author Ollie
 */
public class HistoricVar<C extends Currency> implements Var {

    private final Percentage percentile;
    private final Money<C> atRisk;

    public HistoricVar(final Percentage percentile, final Money<C> atRisk) {
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
