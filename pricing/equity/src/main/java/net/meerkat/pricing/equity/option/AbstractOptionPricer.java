package net.meerkat.pricing.equity.option;

import java.time.temporal.Temporal;
import java.util.Map;

import net.meerkat.Explained;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.derivative.option.Option;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.fx.ExchangeRatesProvider;
import net.meerkat.money.fx.exception.ExchangeRateException;
import net.meerkat.money.price.Price;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.option.OptionPrice;
import net.meerkat.pricing.option.OptionPriceShifts;
import net.ollie.goat.suppliers.lazy.Lazy;

/**
 *
 * @author Ollie
 */
public abstract class AbstractOptionPricer<T extends Temporal, O extends Option<?>> implements OptionPricer<T, O> {

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
        try {
            final ExchangeRates fxRates = this.fxRates.require(date);
            return new PricedOption<>(date, option, currency, fxRates, shifts);
        } catch (final ExchangeRateException ex) {
            throw new InstrumentPriceException(ex);
        }
    }

    /**
     * FIXME put this into the context so it's only calculated once
     *
     * @param <C>
     * @param context
     * @return
     */
    protected abstract <C extends CurrencyId> Price.Valued<C> underlyingPrice(OptionPricingContext<C, O, T> context);

    protected <C extends CurrencyId> Explained<Money<C>> explainIntrinsicValue(final OptionPricingContext<C, O, T> context) {
        final Price.Valued<C> stockPrice = this.underlyingPrice(context);
        final Money<C> exercisePrice = context.exercisePrice();
        final Money<C> intrinsic = stockPrice.value().minus(exercisePrice).times(context.contractMultiplier());
        return new Explained<>(intrinsic, ex -> ex.put("underlying price", stockPrice).put("exercise price", exercisePrice));
    }

    protected abstract <C extends CurrencyId> Explained<Money<C>> explainExtrinsicValue(OptionPricingContext<C, O, T> context);

    private final class PricedOption<C extends CurrencyId>
            implements OptionPrice.Shiftable<C>, OptionPricingContext<C, O, T> {

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

        @Override
        public O option() {
            return option;
        }

        @Override
        public T valuationTime() {
            return date;
        }

        @Override
        public OptionPriceShifts optionShifts() {
            return shifts;
        }

        @Override
        public Price<C> underlyingPrice() {
            return AbstractOptionPricer.this.underlyingPrice(this);
        }

        private final Lazy<Explained<Money<C>>> intrinsic = Lazy.loadOnce(() -> explainIntrinsicValue(this));

        @Override
        public Money<C> intrinsicValue() {
            return intrinsic.get().value();
        }

        private final Lazy<Explained<Money<C>>> extrinsic = Lazy.loadOnce(() -> explainExtrinsicValue(this));

        @Override
        public Money<C> extrinsicValue() {
            final Money<C> extrinsicValue = extrinsic.get().value();
            return extrinsicValue.isPositive()
                    ? extrinsicValue
                    : Money.zero(currencyId);
        }

        @Override
        public PricedOption<C> shift(final OptionPriceShifts shifts) {
            return new PricedOption<>(date, option, currencyId, fxRates, shifts);
        }

        @Override
        public <F extends CurrencyId> Money<C> convert(final Money<F> money) {
            return shifts.shift(fxRates.rate(money.currencyId(), currencyId)).convertAtMid(money);
        }

        @Override
        public Map<String, Object> explain() {
            return this.explanationBuilder()
                    .put("date", date)
                    .put("currency", currencyId)
                    .put("option", option)
                    .put("intrinsic value", intrinsic.get())
                    .put("extrinsic value", extrinsic.get());
        }

    }

}
