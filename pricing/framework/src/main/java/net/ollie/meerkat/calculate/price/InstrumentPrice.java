package net.ollie.meerkat.calculate.price;

import javax.annotation.Nonnull;

import net.meerkat.Explainable;
import net.meerkat.money.Money;
import net.meerkat.money.currency.CurrencyId;
import net.meerkat.money.currency.HasCurrencyId;

/**
 *
 * @author ollie
 */
public interface InstrumentPrice<C extends CurrencyId>
        extends HasCurrencyId, Explainable {

    @Nonnull
    Money<C> clean();

    @Nonnull
    Money<C> dirty();

    @Override
    default C currencyId() {
        return this.clean().currencyId();
    }

    @Nonnull
    default EvaluatedInstrumentPrice<C> evaluate() {
        return new EvaluatedInstrumentPrice<>(this.clean(), this.dirty());
    }

    @Override
    default ExplanationBuilder explain() {
        return this.explanationBuilder()
                .put("clean", this.clean())
                .put("dirty", this.dirty());
    }

}
