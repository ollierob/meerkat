package net.ollie.meerkat.security.bond.future;

import java.util.Set;

import javax.annotation.Nonnull;

import net.ollie.meerkat.security.Security;
import net.ollie.meerkat.security.bond.Bond;

/**
 *
 * @author Ollie
 */
public interface BondBasket extends Security {

    @Nonnull
    Set<Bond> bonds();

    default boolean contains(final Bond bond) {
        return this.bonds().contains(bond);
    }

    static SingleBondBasket of(final Bond bond) {
        return new SingleBondBasket(bond);
    }

}
