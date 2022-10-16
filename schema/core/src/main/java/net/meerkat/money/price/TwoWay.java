package net.meerkat.money.price;

import javax.annotation.CheckForNull;

/**
 *
 * @author ollie
 */
public interface TwoWay<T> {

    @CheckForNull
    T bid();

    @CheckForNull
    T offer();

}
