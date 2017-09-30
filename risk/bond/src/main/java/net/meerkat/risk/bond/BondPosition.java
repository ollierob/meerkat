package net.meerkat.risk.bond;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.calculate.sensitivity.InstrumentSensitivities;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.money.price.Price;
import net.meerkat.pricing.bond.BondPrice;
import net.meerkat.risk.position.PositionId;
import net.meerkat.risk.position.PositionWithQuantity;
import net.meerkat.sensitivity.bond.BondInstrumentSensitivities;

/**
 *
 * @author ollie
 */
public class BondPosition implements PositionWithQuantity {

    private final long quantity;
    private final PositionId positionId;

    public BondPosition(long quantity, PositionId positionId) {
        this.quantity = quantity;
        this.positionId = positionId;
    }

    @Override
    public Long quantity() {
        return quantity;
    }

    @Override
    public PositionId positionId() {
        return positionId;
    }

    @Override
    @Deprecated
    public <C extends CurrencyId> Optional<Money<C>> value(final Price<C> price) {
        return price instanceof BondPrice
                ? Optional.of(this.marketValue((BondPrice<C>) price))
                : Optional.empty();
    }

    @Nonnull
    public <C extends CurrencyId> Money<C> marketValue(@Nonnull final BondPrice<C> price) {
        return price.marketValue(this.quantity());
    }

    @Override
    public Optional<? extends BondPositionSensitivities> scale(final InstrumentSensitivities sensitivities) {
        return sensitivities instanceof BondInstrumentSensitivities
                ? Optional.of(this.scale((BondInstrumentSensitivities) sensitivities))
                : Optional.empty();
    }

    @Nonnull
    public BondPositionSensitivities scale(final BondInstrumentSensitivities sensitivities) {
        return new BondPositionSensitivities(sensitivities, quantity);
    }

}
