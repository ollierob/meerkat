package net.meerkat.instrument.equity.dividend;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.cash.CashPayment;

/**
 *
 * @author Ollie
 */
public interface CashDividend<C extends CurrencyId> extends Dividend, CashPayment<C> {

}
