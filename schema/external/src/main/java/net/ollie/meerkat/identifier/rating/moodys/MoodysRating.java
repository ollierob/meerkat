package net.ollie.meerkat.identifier.rating.moodys;

import net.ollie.meerkat.rating.CreditRating;

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
