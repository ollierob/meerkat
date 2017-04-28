package net.meerkat.instrument.bond.swap;

import java.time.LocalDate;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlTransient;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.coupon.FixedRateCoupon;
import net.meerkat.instrument.derivative.swap.SwapLeg;
import net.meerkat.money.interest.HasInterestRateId;
import net.meerkat.money.interest.InterestRateId;
import net.ollie.goat.temporal.date.HasDate;

/**
 *
 * @author Ollie
 */
@XmlTransient
public class BondAssetSwapLeg<C extends CurrencyId, R extends CurrencyId>
        implements SwapLeg<C, R>, HasDate, HasInterestRateId {

    private FixedRateCoupon<C> coupon;

    private CurrencyId rateCurrency;

    private InterestRateId rateId;

    public BondAssetSwapLeg(final FixedRateCoupon coupon, InterestRateId rateId) {
        this.coupon = coupon;
        this.rateId = rateId;
    }

    @Override
    public LocalDate date() {
        return coupon.date();
    }

    @Nonnull
    public FixedRateCoupon coupon() {
        return coupon;
    }

    @Nonnull
    @Override
    public InterestRateId interestRateId() {
        return rateId;
    }

    @Override
    public C payCurrency() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public R receiveCurrency() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
