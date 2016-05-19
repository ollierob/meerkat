package net.ollie.meerkat.calculate.price;

import javax.annotation.Nonnull;

import net.ollie.meerkat.Explainable;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.identifier.currency.HasCurrencyId;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public interface SecurityPrice<C extends CurrencyId> extends HasCurrencyId, Explainable {

    @Nonnull
    Money<C> clean();

    @Nonnull
    Money<C> dirty();

    @Override
    default C currencyId() {
        return this.clean().currencyId();
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

    class InvalidShiftTypeException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public InvalidShiftTypeException(final String message) {
            super(message);
        }

    }

}
