package net.ollie.meerkat.numeric.money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.BinaryOperator;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.identifier.currency.HasCurrencyId;
import net.ollie.meerkat.numeric.DecimalFraction;
import net.ollie.meerkat.security.Security;
import net.ollie.meerkat.utils.HasName;
import net.ollie.meerkat.utils.numeric.Numbers;
import net.ollie.meerkat.utils.numeric.Numeric;

/**
 *
 * @author Ollie
 */
public interface Money<C extends CurrencyId>
        extends HasCurrencyId, HasName, Numeric.Summable<Money<C>>, Security, Serializable {

    @Override
    C currencyId();

    @Nonnull
    Number amount();

    Money<C> times(Number n);

    @Override
    Money<C> plus(@Nonnull Money<C> that);

    @Override
    default Money<C> minus(final Money<C> that) {
        return this.plus(that.negate());
    }

    @Nonnull
    default DecimalMoney<C> toDecimal() {
        return DecimalMoney.valueOf(this.currencyId(), this.amount());
    }

    @Nonnull
    default Money<C> over(final Number number) {
        return new FractionalMoney<>(this.currencyId(), DecimalFraction.of(this.amount(), number));
    }

    @Override
    default String name() {
        return this.currencyId().toString();
    }

    default String toString(@Nonnull final MoneyFormat convention) {
        return convention.toString(this);
    }

    static <C extends CurrencyId> DecimalMoney<C> zero(final C currency) {
        return new DecimalMoney<>(currency, BigDecimal.ZERO);
    }

    static boolean valuesEqual(final Money<?> left, final Money<?> right) {
        return Objects.equals(left.currencyId(), right.currencyId())
                && Numbers.equals(left.amount(), right.amount());
    }

    static int hashCode(final Money<?> money) {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(money.currencyId());
        hash = 29 * hash + Double.hashCode(money.doubleValue());
        return hash;
    }

    static <C extends CurrencyId> Accumulator.Homogeneous<Money<C>> accumulator() {
        return Money::plus;
    }

    interface Accumulator<C extends CurrencyId> extends net.ollie.meerkat.utils.Accumulator.Homogeneous<Money<C>> {

        static <C extends CurrencyId> BinaryOperator<Money<C>> sumUniformFx() {
            return (left, right) -> left.plus(right);
        }

        @Nonnull
        default BinaryOperator<Money<C>> sumFx() {
            return sumUniformFx();
        }

        @Override
        default Money<C> combine(final Money<C> left, final Money<C> right) {
            return left == null ? right : this.sumFx().apply(left, right);
        }

    }

}
