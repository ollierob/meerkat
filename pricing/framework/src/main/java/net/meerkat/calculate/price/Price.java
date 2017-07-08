package net.meerkat.calculate.price;

import net.meerkat.Explainable;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;

/**
 *
 * @author ollie
 */
public interface Price<C extends CurrencyId> extends HasCurrencyId, Explainable {

}
