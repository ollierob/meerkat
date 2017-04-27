package net.meerkat.instrument.fx.swap;

import java.time.LocalDate;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

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

    @XmlAttribute(name = "valueDate")
    private LocalDate valueDate;

    @XmlElementRef(name = "pay")
    private Money<P> pay;

    @XmlElementRef(name = "receive")
    private Money<R> receive;

    @Deprecated
    FxSwapLeg() {
    }

    public FxSwapLeg(
            final LocalDate valueDate,
            final Money<P> pay,
            final Money<R> receive) {
        this.valueDate = valueDate;
        this.pay = pay;
        this.receive = receive;
    }

    @Nonnull
    public LocalDate valueDate() {
        return valueDate;
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
        return new FxSwapLeg<>(date, receive, pay);
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
                .put("receive", receive);
    }

    public class FxSwapSide<C extends CurrencyId> implements CashPayment<C> {

        private final Money<C> amount;

        private FxSwapSide(final Money<C> amount) {
            this.amount = amount;
        }

        @Override
        public LocalDate date() {
            return valueDate;
        }

        @Override
        public Money<C> amount() {
            return amount;
        }

        @Override
        public <T extends CurrencyId> CashPayment<T> convert(final ExchangeRate<C, T> exchangeRate) {
            return new FxSwapSide<>(amount.convert(exchangeRate));
        }

    }

}
