package net.meerkat.calculate.sensitivity.greeks;

import java.util.Map;

import javax.annotation.CheckForNull;

import net.meerkat.Explainable;

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

}
