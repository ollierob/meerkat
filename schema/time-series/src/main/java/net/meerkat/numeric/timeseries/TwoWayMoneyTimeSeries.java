package net.meerkat.numeric.timeseries;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.price.TwoWayMoney;

/**
 * Bid/ask time series
 *
 * @param <C>
 */
public interface TwoWayMoneyTimeSeries<C extends CurrencyId> extends TimeSeries<TwoWayMoney<C>> {

    default TimeSeries<Money<C>> bidSeries() {
        return this.transform(TwoWayMoney::bid);
    }

    default TimeSeries<Money<C>> askSeries() {
        return this.transform(TwoWayMoney::bid);
    }

    default TimeSeries<Money<C>> midSeries() {
        return this.transform(TwoWayMoney::mid);
    }

    default TimeSeries<Money<C>> spreadSeries() {
        return this.transform(TwoWayMoney::spread);
    }

}
