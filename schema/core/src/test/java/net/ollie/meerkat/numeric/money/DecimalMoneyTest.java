package net.ollie.meerkat.numeric.money;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

import net.ollie.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class DecimalMoneyTest {

    @Test
    public void testToString() {
        final CurrencyId mockCurrency = new CurrencyId() {

            @Override
            public String toString() {
                return "ABC";
            }

        };
        assertThat(new DecimalMoney(mockCurrency, BigDecimal.ONE).toString(), is("ABC 1"));
    }

}
