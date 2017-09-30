package net.meerkat.pricing.equity.option;

import java.util.Map;

import net.meerkat.Explainable.ExplanationBuilder;
import net.meerkat.Explained;
import net.meerkat.calculate.fx.ExchangeRatesProvider;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.derivative.option.Option;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.price.Price;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.option.OptionPrice;
import net.meerkat.pricing.option.OptionPriceShifts;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;
import net.ollie.goat.suppliers.lazy.Lazy;

/**
 *
 * @author Ollie
 */
public abstract class AbstractOptionPricer<T, O extends Option<?>> implements OptionPricer<T, O> {

    private final ExchangeRatesProvider<T> fxRates;

    protected AbstractOptionPricer(final ExchangeRatesProvider<T> fxRates) {
        this.fxRates = fxRates;
    }

    @Override
    public <C extends CurrencyId> OptionPrice.Shiftable<C> price(
            final T date,
            final O option,
            final C currency,
            final OptionPriceShifts shifts)
            throws InstrumentPriceException {
        final ExchangeRates fxRates = this.fxRates.get(date);
        return new PricedOption<>(date, option, currency, fxRates, shifts);
    }

    protected abstract <C extends CurrencyId> Price.Valued<C> underlyingPrice(C currencyId, T date, O option, InstrumentPriceShifts underlyingShifts);

    protected <C extends CurrencyId> Money<C> exercisePrice(final C currencyId, final O option, final ExchangeRates fxRates) {
        return fxRates.convert(option.strike(), currencyId);
    }

    protected <C extends CurrencyId> Explained<Money<C>> intrinsicValue(final C currencyId, final T date, final O option, final ExchangeRates fxRates, final OptionPriceShifts optionShifts) {
        final Price.Valued<C> stockPrice = this.underlyingPrice(currencyId, date, option, optionShifts.underlyingShifts());
        final Money<C> exercisePrice = this.exercisePrice(currencyId, option, fxRates);
        final Money<C> intrinsic = stockPrice.value().minus(exercisePrice).times(option.exercise().contractMultiplier());
        return new Explained<>(intrinsic, new ExplanationBuilder().put("underlying price", stockPrice).put("exercise price", exercisePrice));
    }

    protected abstract <C extends CurrencyId> Explained<Money<C>> timeValue(C currencyId, T date, O warrant, ExchangeRates fxRates);

    private final class PricedOption<C extends CurrencyId> implements OptionPrice.Shiftable<C> {

        private final T date;
        private final O option;
        private final C currencyId;
        private final ExchangeRates fxRates;
        private final OptionPriceShifts shifts;

        PricedOption(final T date, final O option, final C currencyId, final ExchangeRates fxRates, final OptionPriceShifts shifts) {
            this.date = date;
            this.option = option;
            this.currencyId = currencyId;
            this.fxRates = fxRates;
            this.shifts = shifts;
        }

        @Override
        public C currencyId() {
            return currencyId;
        }

        private final Lazy<Explained<Money<C>>> intrinsicValue = Lazy.loadOnce(() -> {
            final PricedOption<C> px = this;
            return AbstractOptionPricer.this.intrinsicValue(px.currencyId, px.date, px.option, px.fxRates, px.shifts);
        });

        Explained<Money<C>> explainedIntrinsicValue() {
            return intrinsicValue.get();
        }

        @Override
        public Money<C> intrinsicValue() {
            return this.explainedIntrinsicValue().value();
        }

        private final Lazy<Explained<Money<C>>> timeValue = Lazy.loadOnce(() -> {
            final PricedOption<C> px = this;
            return AbstractOptionPricer.this.timeValue(px.currencyId, px.date, px.option, px.fxRates);
        });

        Explained<Money<C>> explainedExtrinsicValid() {
            return timeValue.get();
        }

        @Override
        public Money<C> extrinsicValue() {
            return this.explainedExtrinsicValid().value();
        }

        @Override
        public PricedOption<C> shift(final OptionPriceShifts shifts) {
            return new PricedOption<>(date, option, currencyId, fxRates, shifts);
        }

        @Override
        public Map<String, Object> explain() {
            return this.explanationBuilder()
                    .put("date", date)
                    .put("currency", currencyId)
                    .put("option", option)
                    .put("intrinsic value", this.explainedIntrinsicValue())
                    .put("extrinsic value", this.explainedExtrinsicValid());
        }

    }

}
