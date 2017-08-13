package net.meerkat.calculate.var.historic;

import java.time.LocalDate;
import java.util.Map;

import net.meerkat.pricing.shifts.SecurityShifts;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.position.PositionId;

/**
 *
 * @author ollie
 */
public interface HistoricPnlCalculator {

    <C extends CurrencyId> HistoricPnl<C> pnl(
            PositionId positionId,
            C currency,
            LocalDate valuationDate,
            Map<LocalDate, SecurityShifts> shifts);

}
