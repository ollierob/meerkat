package net.meerkat.calculate.var.historic;

import java.math.BigDecimal;
import java.time.LocalDate;
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

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.money.Money;
import net.meerkat.utils.Require;
import net.ollie.goat.collection.list.Lists;
import net.ollie.goat.numeric.interpolation.ListInterpolator;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class HistoricPnl<C extends CurrencyId> implements HasCurrencyId {

    public static <C extends CurrencyId> HistoricPnl<C> daily(final Map<LocalDate, Money<C>> marketValue) {
        final Iterator<Map.Entry<LocalDate, Money<C>>> iterator = marketValue.entrySet().iterator();
        Map.Entry<LocalDate, Money<C>> previous = iterator.next();
        final C currency = previous.getValue().currencyId();
        final NavigableMap<LocalDate, BigDecimal> pnl = new TreeMap<>();
        while (iterator.hasNext()) {
            final Map.Entry<LocalDate, Money<C>> next = iterator.next();
            Require.argumentsEqual(currency, next.getValue().currencyId());
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
    public C currencyId() {
        return currency;
    }

    @Nonnull
    public List<Money<C>> values() {
        return Lists.eagerlyTransform(pnl.values(), bd -> Money.of(currency, bd));
    }

    @Nonnull
    public List<Money<C>> valuesWorstToBest() {
        final List<Money<C>> values = this.values();
        Collections.sort(values);
        return values;
    }

    @Nonnull
    public List<Money<C>> worst(final int n) {
        return this.valuesWorstToBest().subList(0, n);
    }

    public Money<C> valueAtRisk(
            final Percentage percentile,
            final ListInterpolator<Money<C>> interpolator) {
        final List<Money<C>> values = this.valuesWorstToBest();
        final double index = values.size() * 100 / (100.d - percentile.doubleValue());
        return interpolator.interpolate(index, values);
    }

}
