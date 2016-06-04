package net.ollie.meerkat.calculate.var.historic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import static java.util.Objects.requireNonNull;
import java.util.TreeMap;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.money.currency.Currency;
import net.ollie.goat.money.currency.HasCurrency;
import net.ollie.goat.money.Money;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class HistoricPnl<C extends Currency> implements HasCurrency {

    public static <C extends Currency> HistoricPnl<C> from(final Map<LocalDate, Money<C>> values) {
        final Iterator<Map.Entry<LocalDate, Money<C>>> iterator = values.entrySet().iterator();
        Map.Entry<LocalDate, Money<C>> previous = iterator.next();
        final C currency = previous.getValue().currency();
        final NavigableMap<LocalDate, BigDecimal> pnl = new TreeMap<>();
        while (iterator.hasNext()) {
            final Map.Entry<LocalDate, Money<C>> next = iterator.next();
            final Money<C> diff = next.getValue().minus(previous.getValue());
            pnl.put(next.getKey(), diff.decimalValue());
            previous = next;
        }
        return new HistoricPnl<>(currency, pnl);
    }

    @XmlElementRef(name = "currency")
    private C currency;

    @XmlElementWrapper(name = "pnl")
    private NavigableMap<LocalDate, BigDecimal> pnl;

    @Deprecated
    HistoricPnl() {
    }

    public HistoricPnl(final C currency, final NavigableMap<LocalDate, BigDecimal> pnl) {
        this.currency = requireNonNull(currency);
        this.pnl = requireNonNull(pnl);
    }

    @Override
    public C currency() {
        return currency;
    }

    @Nonnull
    public List<BigDecimal> values() {
        return new ArrayList<>(pnl.values());
    }

    @Nonnull
    public List<BigDecimal> valuesWorstToBest() {
        final List<BigDecimal> values = this.values();
        Collections.sort(values);
        return values;
    }

    @Nonnull
    public List<BigDecimal> worst(final int n) {
        return this.valuesWorstToBest().subList(0, n);
    }

    @Nonnull
    public NavigableMap<LocalDate, BigDecimal> byDate() {
        return new TreeMap<>(pnl);
    }

}
