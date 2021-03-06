package net.meerkat.instrument.fx.swap;

import java.time.LocalDate;
import java.util.Map;

import javax.annotation.Nonnull;

import net.meerkat.Explainable;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.identifier.currency.HasCurrencyIds;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.instrument.derivative.swap.SwapLeg;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;

/**
 *
 * @author ollie
 */
public class FxSwapLeg<P extends CurrencyId, R extends CurrencyId>
        implements SwapLeg<P, R>, HasCurrencyIds, Explainable {

    private final LocalDate valueDate;
    private final Money<P> pay;
    private final Money<R> receive;
    private final LocalDate maturityDate;

    public FxSwapLeg(
            final LocalDate valueDate,
            final Money<P> pay,
            final Money<R> receive,
            final LocalDate maturityDate) {
        this.valueDate = valueDate;
        this.pay = pay;
        this.receive = receive;
        this.maturityDate = maturityDate;
    }

    @Nonnull
    public LocalDate valueDate() {
        return valueDate;
    }

    @Override
    public LocalDate deliveryDate() {
        return maturityDate;
    }

    @Override
    public P payCurrency() {
        return pay.currencyId();
    }

    @Override
    public R receiveCurrency() {
        return receive.currencyId();
    }

    @Nonnull
    public Money<P> pay() {
        return pay;
    }

    @Nonnull
    public FxSwapSide<P> paySide() {
        return new FxSwapSide<>(pay);
    }

    @Nonnull
    public Money<R> receive() {
        return receive;
    }

    @Nonnull
    public FxSwapSide<R> receiveSide() {
        return new FxSwapSide<>(receive);
    }

    public FxSwapLeg<R, P> inverse(final LocalDate date) {
        return new FxSwapLeg<>(date, receive, pay, maturityDate);
    }

    @Override
    public CurrencyIds currencyIds() {
        return CurrencyIds.of(pay.currencyId(), receive.currencyId());
    }

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("value date", valueDate)
                .put("pay", pay)
                .put("receive", receive)
                .put("maturity date", maturityDate);
    }

    public class FxSwapSide<C extends CurrencyId> implements CashPayment<C> {

        private final Money<C> amount;

        private FxSwapSide(final Money<C> amount) {
            this.amount = amount;
        }

        @Override
        public LocalDate paymentDate() {
            return valueDate;
        }

        @Override
        public Money<C> paymentAmount() {
            return amount;
        }

        @Override
        public <T extends CurrencyId> CashPayment<T> convert(final ExchangeRate<C, T> exchangeRate) {
            return new FxSwapSide<>(amount.convert(exchangeRate));
        }

    }

}
