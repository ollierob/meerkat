package net.meerkat.money.fx;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.identifier.currency.HasCurrencyIds;
import net.meerkat.money.Money;
import net.meerkat.money.price.TwoWayMoney;
import net.ollie.goat.numeric.fraction.BigDecimalFraction;

/**
 *
 * @author Ollie
 * @see ExchangeRates
 */
public interface ExchangeRate<F extends CurrencyId, T extends CurrencyId>
        extends Comparable<ExchangeRate<F, T>>, HasCurrencyIds {

    @Nonnull
    F from();

    @Nonnull
    T to();

    @Nonnull
    BigDecimalFraction bidRate();

    @Nonnull
    BigDecimalFraction offerRate();

    default BigDecimalFraction spread() {
        return this.offerRate().minus(this.bidRate());
    }

    default BigDecimalFraction midRate() {
        return this.bidRate().plus(this.offerRate()).over(2);
    }

    default TwoWayMoney<T> convert(final TwoWayMoney<F> from) {
        final Money<T> bid = Money.of(this.to(), this.bidRate().times(from.bid().amount())); //TODO extract method
        final Money<T> offer = Money.of(this.to(), this.offerRate().times(from.offer().amount()));
        return TwoWayMoney.of(bid, offer);
    }

    default Money<T> convertAtMid(final TwoWayMoney<F> from) {
        return this.convertAtMid(from.mid());
    }

    default Money<T> convertAtMid(final Money<F> from) {
        return Money.of(this.to(), this.midRate().times(from.amount()));
    }

    default Money<F> convertAtMidFrom(final Money<T> from) {
        return Money.of(this.from(), this.midRate().over(from.amount()));
    }

    default ExchangeRate<T, F> inverse() {
        return new InverseExchangeRate<>(this);
    }

    default <X extends CurrencyId> ExchangeRate<F, X> triangulate(final ExchangeRate<T, X> that) {
        return new TriangulatedExchangeRate<>(this, that);
    }

    @Override
    default int compareTo(final ExchangeRate<F, T> that) {
        return this.midRate().compareTo(that.midRate());
    }

    @Override
    default CurrencyIds currencyIds() {
        return CurrencyIds.of(this.from(), this.to());
    }

    static <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> between(final Money<F> from, final Money<T> to) {
        return new RatioExchangeRate<>(from, to);
    }

    static <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> between(final F from, final T to, final Number midRate) {
        return ReferenceExchangeRate.ofMid(from, to, BigDecimalFraction.of(midRate));
    }

}
