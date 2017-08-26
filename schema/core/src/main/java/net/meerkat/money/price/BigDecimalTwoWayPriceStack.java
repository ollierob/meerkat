package net.meerkat.money.price;

import java.math.BigDecimal;
import java.util.Map;

import net.coljate.list.ImmutableList;
import net.coljate.list.List;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
public class BigDecimalTwoWayPriceStack<C extends CurrencyId> implements TwoWayPriceStack<C> {

    private final C currencyId;
    private final BigDecimal[] bids;
    private final BigDecimal[] offers;

    public BigDecimalTwoWayPriceStack(final C currencyId, final BigDecimal[] bids, final BigDecimal[] offers) {
        this.currencyId = currencyId;
        this.bids = bids;
        this.offers = offers;
    }

    @Override
    public ImmutableList<Money<C>> bids() {
        return List.of(bids).transform(this::toMoney).immutableCopy();
    }

    @Override
    public Money<C> bestBid() {
        return bids.length == 0
                ? null
                : this.toMoney(bids[0]);
    }

    private Money<C> toMoney(final BigDecimal amount) {
        return Money.of(currencyId, amount);
    }

    @Override
    public ImmutableList<Money<C>> offers() {
        return List.of(offers).transform(this::toMoney).immutableCopy();
    }

    @Override
    public C currencyId() {
        return currencyId;
    }

    @Override
    @Deprecated
    public BigDecimalTwoWayPriceStack<C> evaluate() {
        return this;
    }

    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("currency", currencyId)
                .put("bids", bids)
                .put("offers", offers);
    }

}
