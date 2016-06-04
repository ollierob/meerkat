package net.ollie.meerkat.numeric.money;

import net.ollie.goat.money.DecimalMoney;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

import net.ollie.goat.currency.CurrencyId;

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
