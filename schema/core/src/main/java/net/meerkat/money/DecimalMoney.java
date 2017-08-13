package net.meerkat.money;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.ollie.goat.numeric.BigDecimals;

/**
 *
 * @author Ollie
 */
public class DecimalMoney<C extends CurrencyId>
        implements Money<C> {

    private static final long serialVersionUID = 1L;

    public static <C extends CurrencyId> DecimalMoney<C> valueOf(final Money<C> money) {
        return money instanceof DecimalMoney
                ? (DecimalMoney<C>) money
                : valueOf(money.currencyId(), money.amount());
    }

    public static <C extends CurrencyId> DecimalMoney<C> valueOf(final C currency, final Number amount) {
        return new DecimalMoney<>(currency, BigDecimals.toBigDecimal(amount));
    }

    public static <C extends CurrencyId> DecimalMoney<C> valueOf(final C currency, final double amount) {
        return new DecimalMoney<>(currency, BigDecimal.valueOf(amount));
    }

    private final C currency;
    private final BigDecimal amount;

    public DecimalMoney(@Nonnull final C currency, @Nonnull final BigDecimal amount) {
        this.currency = Objects.requireNonNull(currency, "currency");
        this.amount = Objects.requireNonNull(amount, "amount");
    }

    @Override
    public C currencyId() {
        return currency;
    }

    @Override
    public BigDecimal amount() {
        return amount;
    }

    @Override
    public boolean isZero() {
        return amount.signum() == 0;
    }

    @Override
    public Money<C> plus(final Money<C> that) {
        return that.isZero()
                ? this
                : new DecimalMoney<>(currency, amount.add(that.decimalValue()));
    }

    @Override
    public DecimalMoney<C> times(final Number n) {
        return new DecimalMoney<>(currency, amount.multiply(BigDecimals.toBigDecimal(n)));
    }

    @Override
    @Deprecated
    public Money<C> times(final Number that, final RoundingMode rounding) {
        return this.times(that);
    }

    @Override
    public DecimalMoney<C> negate() {
        return new DecimalMoney<>(currency, amount.negate());
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return amount.round(context);
    }

    @Override
    public DecimalMoney<C> toDecimal() {
        return this;
    }

    @Override
    public String toString() {
        return this.toString(currency.format());
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Money
                && Money.valuesEqual(this, (Money) obj);
    }

    @Override
    public int hashCode() {
        return Money.hashCode(this);
    }

}
