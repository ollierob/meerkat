package net.ollie.meerkat.numeric.money.fx;

import java.math.BigDecimal;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.DecimalMoney;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.utils.numeric.Numbers;

/**
 *
 * @author Ollie
 */
public interface ExchangeRate<F extends CurrencyId, T extends CurrencyId> {

    @Nonnull
    F from();

    @Nonnull
    T to();

    @Nonnull
    BigDecimal rate();

    default Money<T> convert(final Money<F> from) {
        return new DecimalMoney<>(this.to(), this.rate().multiply(Numbers.toBigDecimal(from.amount())));
    }

}
