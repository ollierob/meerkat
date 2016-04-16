package net.ollie.meerkat.calculate.var.historic;

import net.ollie.meerkat.calculate.var.Var;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.utils.numeric.Percentage;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author Ollie
 */
public class HistoricVar<C extends CurrencyId> implements Var {

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
