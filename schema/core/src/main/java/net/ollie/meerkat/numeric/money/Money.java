package net.ollie.meerkat.numeric.money;

import java.util.Objects;
import java.util.function.BinaryOperator;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.identifier.currency.HasCurrencyId;
import net.ollie.meerkat.security.Security;
import net.ollie.meerkat.utils.numeric.Numbers;
import net.ollie.meerkat.utils.numeric.Numeric;

/**
 *
 * @author Ollie
 */
public interface Money<C extends CurrencyId> extends HasCurrencyId, Numeric<Money<C>>, Security {

    @Override
    C currency();

    @Nonnull
    Number amount();

    @Nonnull
    Money<C> plus(@Nonnull Money<C> that);

    default Money<C> minus(final Money<C> that) {
        return this.plus(that.negate());
    }

    @Nonnull
    default DecimalMoney<C> toDecimal() {
        return DecimalMoney.valueOf(this.currency(), this.amount());
    }

    @Override
    default String name() {
        return this.currency().toString();
    }

    default String toString(@Nonnull final MoneyFormat convention) {
        return convention.toString(this);
    }

    static boolean valuesEqual(final Money<?> left, final Money<?> right) {
        return Objects.equals(left.currency(), right.currency())
                && Numbers.equals(left.amount(), right.amount());
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
