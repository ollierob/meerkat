package net.ollie.meerkat.numeric.money;

import java.math.BigDecimal;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

import net.ollie.goat.currency.Currency;
import net.ollie.goat.money.DecimalMoney;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class DecimalMoneyTest {

    @Test
    public void testToString() {
        final Currency mockCurrency = new Currency() {

            @Override
            public String toString() {
                return "ABC";
            }

            @Override
            public String symbol() {
                throw new UnsupportedOperationException(); //TODO
            }

            @Override
            public String uniqueSymbol() {
                throw new UnsupportedOperationException(); //TODO
            }

            @Override
            public Currency currency() {
                throw new UnsupportedOperationException(); //TODO
            }

            @Override
            public Set<? extends Currency> currencies() {
                throw new UnsupportedOperationException(); //TODO
            }

        };
        assertThat(new DecimalMoney(mockCurrency, BigDecimal.ONE).toString(), is("ABC 1"));
    }

}
