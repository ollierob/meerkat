package net.meerkat.sensitiity.moneymarket;

import net.meerkat.calculate.sensitivity.InterestRateSensitivities;
import net.meerkat.pricing.moneymarket.RepoPrice;

/**
 *
 * @author ollie
 */
public interface RepoSensitivities extends InterestRateSensitivities {

    @Override
    RepoPrice.Evaluated<?> price();

}
