package net.ollie.meerkat.calculate.var;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import static java.util.Objects.requireNonNull;
import java.util.TreeMap;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.identifier.currency.HasCurrencyId;
import net.ollie.meerkat.numeric.money.Money;

import org.threeten.extra.Interval;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class HistoricPnl<C extends CurrencyId> implements HasCurrencyId {

    public static <C extends CurrencyId> HistoricPnl<C> from(final Map<Instant, Money<C>> values) {
        final Iterator<Map.Entry<Instant, Money<C>>> iterator = values.entrySet().iterator();
        Map.Entry<Instant, Money<C>> previous = iterator.next();
        final C currency = previous.getValue().currencyId();
        final NavigableMap<Interval, BigDecimal> pnl = new TreeMap<>(HistoricPnl::compareIntervals);
        while (iterator.hasNext()) {
            final Map.Entry<Instant, Money<C>> next = iterator.next();
            final Money<C> diff = next.getValue().minus(previous.getValue());
            final Interval interval = Interval.of(previous.getKey(), next.getKey());
            pnl.put(interval, diff.decimalValue());
            previous = next;
        }
        return new HistoricPnl<>(currency, pnl);
    }

    @XmlElementRef(name = "currency")
    private C currency;

    @XmlElementWrapper(name = "pnl")
    private NavigableMap<Interval, BigDecimal> pnl;

    @Deprecated
    HistoricPnl() {
    }

    public HistoricPnl(final C currency, final NavigableMap<Interval, BigDecimal> pnl) {
        this.currency = requireNonNull(currency);
        this.pnl = requireNonNull(pnl);
    }

    @Override
    public C currencyId() {
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
    public NavigableMap<Interval, BigDecimal> byTime() {
        return new TreeMap<>(pnl);
    }

    @Nonnull
    public NavigableSet<Interval> intervals() {
        return this.byTime().navigableKeySet();
    }

    static int compareIntervals(final Interval left, final Interval right) {
        return left.getStart().compareTo(right.getStart());
    }

}
