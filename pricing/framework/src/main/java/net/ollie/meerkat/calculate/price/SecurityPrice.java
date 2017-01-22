package net.ollie.meerkat.calculate.price;

import javax.annotation.Nonnull;

import net.ollie.meerkat.money.Money;
import net.ollie.meerkat.money.currency.Currency;
import net.ollie.meerkat.money.currency.HasCurrency;
import net.ollie.meerkat.Explainable;

/**
 *
 * @author ollie
 */
public interface SecurityPrice<C extends Currency> extends HasCurrency, Explainable {

    @Nonnull
    Money<C> clean();

    @Nonnull
    Money<C> dirty();

    @Override
    default C currency() {
        return this.clean().currency();
    }

    @Nonnull
    default EvaluatedSecurityPrice<C> evaluate() {
        return new EvaluatedSecurityPrice<>(this.clean(), this.dirty());
    }

    @Override
    default ExplanationBuilder explain() {
        return new ExplanationBuilder()
                .put("clean", this.clean())
                .put("dirty", this.dirty());
    }

}
