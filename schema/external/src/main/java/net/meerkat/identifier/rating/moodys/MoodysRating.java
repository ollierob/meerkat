package net.meerkat.identifier.rating.moodys;

import net.meerkat.risk.rating.CreditRating;

/**
 *
 * @author Ollie
 */
public interface MoodysRating extends CreditRating {

    @Override
    public default String agency() {
        return "Moodys";
    }

}
