package net.ollie.meerkat.security.fx.swap;

import java.time.LocalDate;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.money.currency.Currency;
import net.ollie.meerkat.money.Money;
import net.ollie.meerkat.security.derivative.swap.SwapLeg;
import net.ollie.meerkat.security.fx.CashPayment;

/**
 *
 * @author ollie
 */
public class FxSwapLeg implements SwapLeg {

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

    public class FxSwapSide<C extends Currency> implements CashPayment<C> {

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

    }

}
