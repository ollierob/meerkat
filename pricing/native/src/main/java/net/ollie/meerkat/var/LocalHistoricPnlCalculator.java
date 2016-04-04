package net.ollie.meerkat.var;

import com.google.common.collect.Maps;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Function;

import net.ollie.meerkat.calculate.price.SecurityPrice;
import net.ollie.meerkat.calculate.price.SecurityPricer;
import net.ollie.meerkat.calculate.price.shifts.SecurityShifts;
import net.ollie.meerkat.calculate.var.historic.HistoricPnl;
import net.ollie.meerkat.calculate.var.historic.HistoricPnlCalculator;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.identifier.security.SecurityId;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.SecurityDefinition;

/**
 *
 * @author ollie
 */
public class LocalHistoricPnlCalculator implements HistoricPnlCalculator {

    private final Function<SecurityId, SecurityDefinition> getSecurityDefinition;
    private final SecurityPricer<LocalDate> pricer;
    private final ExecutorService executor;

    public LocalHistoricPnlCalculator(
            final Function<SecurityId, SecurityDefinition> getSecurityDefinition,
            final SecurityPricer<LocalDate> pricer,
            final ExecutorService executor) {
        this.getSecurityDefinition = getSecurityDefinition;
        this.pricer = pricer;
        this.executor = executor;
    }

    @Override
    public <C extends CurrencyId> HistoricPnl<C> pnl(
            final SecurityId securityId,
            final C currency,
            final LocalDate valuation,
            final Map<LocalDate, SecurityShifts> shifts) {
        final Future<HistoricPnl<C>> future = CompletableFuture.supplyAsync(() -> getSecurityDefinition.apply(securityId), executor)
                .thenApplyAsync(security -> pricer.price(valuation, security, currency), executor)
                .thenApplyAsync(basePrice -> this.pnl(basePrice, shifts), executor);
        return this.complete(future);
    }

    private <C extends CurrencyId> HistoricPnl<C> pnl(
            final SecurityPrice<C> basePrice,
            final Map<LocalDate, SecurityShifts> shifts) {
        final Map<LocalDate, Callable<SecurityPrice<C>>> shifters = Maps.transformValues(shifts, shift -> () -> basePrice.shift(shift));
        final Map<LocalDate, Future<SecurityPrice<C>>> scenarios = Maps.transformValues(shifters, executor::submit);
        final Map<LocalDate, SecurityPrice<C>> securityPrices = Maps.transformValues(scenarios, this::complete);
        final Map<LocalDate, Money<C>> dirtyPrices = Maps.transformValues(securityPrices, SecurityPrice::dirtyValue);
        return HistoricPnl.from(dirtyPrices);
    }

    private <T> T complete(final Future<T> future) {
        try {
            return future.get();
        } catch (final InterruptedException | ExecutionException ex) {
            throw new HistoricPnlCalculationException(ex);
        }
    }

}
