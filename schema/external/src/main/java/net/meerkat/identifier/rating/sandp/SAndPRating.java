package net.meerkat.identifier.rating.sandp;

import net.meerkat.rating.CreditRating;

/**
 *
 * @author ollie
 * @see
 * <a href="https://www.standardandpoors.com/en_US/web/guest/article/-/view/sourceId/504352#ID3753">S&amp;P
 * ratings</a>
 */
public interface SAndPRating extends CreditRating {

    @Override
    default String agency() {
        return "Standard & Poor's";
    }

}
