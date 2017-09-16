package net.meerkat.pricing.shifts;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
public interface PriceShifts extends SecurityShifts {

    @Nonnull
    <C extends CurrencyId> Money<C> shift(@Nonnull Money<C> price);

    @Nonnull
    static PriceShifts cast(final SecurityShifts shifts) {
        return shifts instanceof PriceShifts
                ? (PriceShifts) shifts
                : shifts.as(PriceShifts.class).orElseGet(PriceShifts::none);
    }

    static PriceShifts none() {
        return NoPriceShifts.INSTANCE;
    }

}
