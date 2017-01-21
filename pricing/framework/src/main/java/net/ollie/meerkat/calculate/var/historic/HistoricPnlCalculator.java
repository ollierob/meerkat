package net.ollie.meerkat.calculate.var.historic;

import java.time.LocalDate;
import java.util.Map;

import net.ollie.goat.money.currency.Currency;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.ollie.meerkat.identifier.security.SecurityId;

/**
 *
 * @author ollie
 */
public interface HistoricPnlCalculator {

    <C extends Currency> HistoricPnl<C> pnl(
            SecurityId security,
            C currency,
            LocalDate valuation,
            Map<LocalDate, SecurityShifts> shifts);

}
