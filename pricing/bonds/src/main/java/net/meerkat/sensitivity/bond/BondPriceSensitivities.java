package net.meerkat.sensitivity.bond;

import net.meerkat.calculate.sensitivity.InterestRateSensitivities;
import net.meerkat.pricing.bond.BondPrice;
import net.meerkat.pricing.bond.EvaluatedBondPrice;

/**
 *
 * @author Ollie
 * @see BondPrice
 */
public interface BondPriceSensitivities extends InterestRateSensitivities {

    @Override
    EvaluatedBondPrice<?> price();

}
