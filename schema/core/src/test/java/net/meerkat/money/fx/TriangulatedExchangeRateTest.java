package net.meerkat.money.fx;

import net.meerkat.identifier.currency.StubCurrencyId;
import net.meerkat.money.price.TwoWayDecimalMoney;
import net.meerkat.money.price.TwoWayMoney;
import net.meerkat.numeric.BigDecimals;
import net.meerkat.numeric.decimal.BigDecimalFraction;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class TriangulatedExchangeRateTest {

    @Test
    public void shouldTriangulateTwoWayMoney() {

        final ExchangeRate<GBP, USD> gbpToUsd = mock(ExchangeRate.class);
        when(gbpToUsd.from()).thenReturn(GBP.GBP);
        when(gbpToUsd.to()).thenReturn(USD.USD);
        when(gbpToUsd.bidRate()).thenReturn(BigDecimalFraction.of(1.33));
        when(gbpToUsd.offerRate()).thenReturn(BigDecimalFraction.of(1.34));

        final ExchangeRate<USD, MXN> usdToMxn = mock(ExchangeRate.class);
        when(usdToMxn.from()).thenReturn(USD.USD);
        when(usdToMxn.to()).thenReturn(MXN.MXN);
        when(usdToMxn.bidRate()).thenReturn(BigDecimalFraction.of(18.91));
        when(usdToMxn.offerRate()).thenReturn(BigDecimalFraction.of(18.92));

        final ExchangeRate<GBP, MXN> gbpToMxn = new TriangulatedExchangeRate<>(gbpToUsd, usdToMxn);

        final TwoWayMoney<GBP> gbp = new TwoWayDecimalMoney(GBP.GBP, BigDecimals.ONE, BigDecimals.TWO);
        final TwoWayMoney<MXN> mxn = gbpToMxn.convert(gbp);

        assertThat(mxn.bid().value().doubleValue(), closeTo(1 * 1.33 * 18.91, 1e-10));
        assertThat(mxn.offer().value().doubleValue(), closeTo(2 * 1.34 * 18.92, 1e-10));

    }

    static class GBP extends StubCurrencyId {

        static final GBP GBP = new GBP();

        public GBP() {
            super("GBP");
        }

    }

    static class USD extends StubCurrencyId {

        static final USD USD = new USD();

        public USD() {
            super("USD");
        }

    }

    static class MXN extends StubCurrencyId {

        static final MXN MXN = new MXN();

        public MXN() {
            super("MXN");
        }

    }

}
