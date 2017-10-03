package net.meerkat.money.price;

/**
 *
 * @author ollie
 */
public interface TwoWay<T> {

    T bid();

    T offer();

    T mid();

}
