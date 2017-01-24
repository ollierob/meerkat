package net.ollie.meerkat.calculate.var.historic;

import java.time.LocalDate;
import java.util.Map;

import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface HistoricPnlCalculator {

    <C extends CurrencyId> HistoricPnl<C> pnl(
            InstrumentId security,
            C currency,
            LocalDate valuation,
            Map<LocalDate, SecurityShifts> shifts);

}
