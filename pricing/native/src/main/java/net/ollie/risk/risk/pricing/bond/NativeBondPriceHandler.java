package net.ollie.risk.risk.pricing.bond;

import java.time.LocalDate;

import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondPriceHandler;
import net.ollie.meerkat.calculate.price.bond.BondPricer;
import net.ollie.meerkat.calculate.price.bond.BondShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.ConvertibleBond;
import net.ollie.meerkat.security.bond.FixedCouponBond;
import net.ollie.meerkat.security.bond.FloatingRateNote;
import net.ollie.meerkat.security.bond.PerpetualBond;

/**
 *
 * @author ollie
 */
public class NativeBondPriceHandler implements BondPriceHandler {

    private final BondPricer<PerpetualBond> perpetualPricer;
    private final BondPricer<FixedCouponBond> fixedCouponPricer;
    private final BondPricer<FloatingRateNote> floatingNotePricer;
    private final BondPricer<ConvertibleBond> convertiblePricer;

    public NativeBondPriceHandler(
            final BondPricer<PerpetualBond> perpetualPricer,
            final BondPricer<FixedCouponBond> fixedCouponPricer,
            final BondPricer<FloatingRateNote> floatingNotePricer,
            final BondPricer<ConvertibleBond> convertiblePricer) {
        this.perpetualPricer = perpetualPricer;
        this.fixedCouponPricer = fixedCouponPricer;
        this.floatingNotePricer = floatingNotePricer;
        this.convertiblePricer = convertiblePricer;
    }

    @Override
    public <C extends CurrencyId> BondPriceContext<C> priceContext(final LocalDate date, final C currency, final BondShifts shifts) {
        return new BondPriceContext<C>() {

            @Override
            public BondPrice<C> handle(final FixedCouponBond bond) {
                return fixedCouponPricer.price(date, bond, shifts, currency);
            }

            @Override
            public BondPrice<C> handle(final FloatingRateNote bond) {
                return floatingNotePricer.price(date, bond, shifts, currency);
            }

            @Override
            public BondPrice<C> handle(final PerpetualBond bond) {
                return perpetualPricer.price(date, bond, shifts, currency);
            }

            @Override
            public BondPrice<C> handle(final ConvertibleBond bond) {
                return convertiblePricer.price(date, bond, shifts, currency);
            }

        };
    }

}
