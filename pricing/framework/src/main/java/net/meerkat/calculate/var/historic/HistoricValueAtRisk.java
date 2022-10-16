package net.meerkat.calculate.var.historic;

import net.meerkat.calculate.var.ValueAtRisk;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.numeric.percentage.Percentage;

/**
 * @author Ollie
 */
public record HistoricValueAtRisk<C extends CurrencyId>(Percentage percentile, Money<C> atRisk) implements ValueAtRisk {

}
