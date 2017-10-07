package net.meerkat.calculate.sensitivity.greeks;

import java.util.Map;

import javax.annotation.CheckForNull;

import net.meerkat.Explainable;
import net.meerkat.calculate.sensitivity.PriceSensitivitiesMapper;

/**
 *
 * @author ollie
 */
public interface HasGreeks extends Explainable {

    @CheckForNull
    Delta delta();

    @CheckForNull
    Gamma gamma();

    @CheckForNull
    Theta theta();

    @CheckForNull
    Vega vega();

    @CheckForNull
    Rho rho();

    @Override
    default Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("delta", this.delta())
                .put("gamma", this.gamma())
                .put("theta", this.theta())
                .put("vega", this.vega())
                .put("rho", this.rho());
    }

    PriceSensitivitiesMapper<HasGreeks> GREEKS = PriceSensitivitiesMapper.<HasGreeks>builder()
            .put(Delta.ID, HasGreeks::delta)
            .put(Gamma.ID, HasGreeks::gamma)
            .put(Theta.ID, HasGreeks::theta)
            .put(Rho.ID, HasGreeks::rho)
            .put(Vega.ID, HasGreeks::vega)
            .build();

}
