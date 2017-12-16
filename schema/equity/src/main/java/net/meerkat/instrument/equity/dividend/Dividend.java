package net.meerkat.instrument.equity.dividend;

import net.meerkat.Explainable;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.money.Money;
import net.meerkat.numeric.percentage.Percentage;
import net.meerkat.temporal.date.HasDate;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author Ollie
 */
public interface Dividend<C extends CurrencyId> extends HasDate, Explainable {

    /**
     * @return the date this dividend is paid.
     */
    @Nonnull
    @Override
    @Deprecated
    default LocalDate date() {
        return this.dates().payable();
    }

    @Nonnull
    Dividend.Dates dates();

    @Nonnull
    Percentage stockIncrease();

    @Nonnull
    Money<C> cashPerShare();

    @CheckForNull
    default CashPayment<C> paymentPerShare() {
        final Money<C> paid = this.cashPerShare();
        return paid.isZero() ? null : CashPayment.of(this.date(), paid);
    }

    @Nonnull
    @Override
    default Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("dates", this.dates())
                .put("stock", this.stockIncrease())
                .put("cash", this.cashPerShare());
    }

    interface Dates extends Explainable {

        /**
         * @return the date the dividend payment was declared.
         */
        @CheckForNull
        LocalDate declaration();

        /**
         * @return the date of record, i.e. the date by which a shareholder must be on the company's books to receive this dividend.
         */
        @CheckForNull
        LocalDate record();

        /**
         * @return the date on which sellers rather than buyers will receive this dividend.
         */
        @CheckForNull
        LocalDate exDividend();

        @Nonnull
        LocalDate payable();

        @Nonnull
        @Override
        default Map<String, Object> explain() {
            return this.explanationBuilder()
                    .put("declaration", this.declaration())
                    .put("record", this.record())
                    .put("ex-dividend", this.exDividend())
                    .put("payable", this.payable());
        }
    }

}
