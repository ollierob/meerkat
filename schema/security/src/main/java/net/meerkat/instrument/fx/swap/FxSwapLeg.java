package net.meerkat.instrument.fx.swap;

import java.time.LocalDate;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.Explainable;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.instrument.derivative.swap.SwapLeg;
import net.meerkat.money.Money;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.fx.ExchangeRate;

/**
 *
 * @author ollie
 */
public class FxSwapLeg implements SwapLeg, Explainable {

    @XmlAttribute(name = "date")
    private LocalDate date;

    @XmlElementRef(name = "pay")
    private Money<?> pay;

    @XmlElementRef(name = "receive")
    private Money<?> receive;

    @Deprecated
    FxSwapLeg() {
    }

    public FxSwapLeg(
            final LocalDate date,
            final Money<?> pay,
            final Money<?> receive) {
        this.date = date;
        this.pay = pay;
        this.receive = receive;
    }

    @Nonnull
    public LocalDate date() {
        return date;
    }

    @Nonnull
    public Money<?> pay() {
        return pay;
    }

    @Nonnull
    public FxSwapSide<?> paySide() {
        return new FxSwapSide<>(pay);
    }

    @Nonnull
    public Money<?> receive() {
        return receive;
    }

    @Nonnull
    public FxSwapSide<?> receiveSide() {
        return new FxSwapSide<>(receive);
    }

    public FxSwapLeg inverse(final LocalDate date) {
        return new FxSwapLeg(date, receive, pay);
    }

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("date", date)
                .put("pay", pay)
                .put("receive", receive);
    }

    public class FxSwapSide<C extends CurrencyId> implements CashPayment<C> {

        private final Money<C> amount;

        public FxSwapSide(final Money<C> amount) {
            this.amount = amount;
        }

        @Override
        public LocalDate date() {
            return date;
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