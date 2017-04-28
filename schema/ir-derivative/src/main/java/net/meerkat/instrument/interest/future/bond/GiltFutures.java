package net.meerkat.instrument.interest.future.bond;

import java.time.Period;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.currency.GBP;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.money.Money;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.months.Months;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class GiltFutures extends GenericBondFutureBasket<GBP> {

    private static final long serialVersionUID = 1L;

    public static GiltFutures shortContract(String name, InstrumentIds ids, Set<InstrumentId> deliverable, Percentage nominalCoupon) {
        return new GiltFutures(name, ids, deliverable, Period.of(1, 6, 0), Period.of(3, 3, 0), nominalCoupon, GBP.ONE_HUNDRED_THOUSAND, MAR_JUN_SEP_DEC);
    }

    public static GiltFutures ultraLongContract(String name, InstrumentIds ids, Set<InstrumentId> deliverable, Percentage nominalCoupon) {
        return new GiltFutures(name, ids, deliverable, Period.ofYears(28), Period.ofYears(37), nominalCoupon, GBP.ONE_HUNDRED_THOUSAND, MAR_JUN_SEP_DEC);
    }

    public GiltFutures(String name, InstrumentIds ids, Set<InstrumentId> deliverable, Period minimumMaturity, Period maximumMaturity, Percentage nominalCoupon, Money<GBP> notional, Months deliveryMonths) {
        super(name, ids, deliverable, minimumMaturity, maximumMaturity, nominalCoupon, notional, deliveryMonths);
    }

}
