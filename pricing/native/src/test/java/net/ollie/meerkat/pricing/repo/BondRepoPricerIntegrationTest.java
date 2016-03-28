package net.ollie.meerkat.pricing.repo;

import java.time.LocalDate;
import java.time.Month;

import net.ollie.meerkat.IntegrationTest;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondPriceHandler;
import net.ollie.meerkat.calculate.price.bond.BondShifts;
import net.ollie.meerkat.calculate.price.repo.RepoPrice;
import net.ollie.meerkat.identifier.currency.CurrencyIso;
import net.ollie.meerkat.identifier.currency.USD;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.SimpleFixedInterestRate;
import net.ollie.meerkat.numeric.interest.daycount.ActualFixedAccrualFactor;
import net.ollie.meerkat.numeric.money.DecimalMoney;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.FixedCouponBond;
import net.ollie.meerkat.security.repo.BondRepo;
import net.ollie.meerkat.security.repo.dates.RepoDates;
import net.ollie.meerkat.security.repo.dates.TermRepoDates;
import net.ollie.meerkat.security.repo.rate.RepoInterestRate;
import net.ollie.meerkat.security.repo.rate.RepoRate;

import org.hamcrest.Matchers;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author ollie
 */
@Category(IntegrationTest.class)
public class BondRepoPricerIntegrationTest {

    private BondRepoPricer testRepoPricer;
    @Mock
    private BondPriceHandler mockBondPricer;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        testRepoPricer = new BondRepoPricer(mockBondPricer);
    }

    @Test
    public void testExample() {

        final USD usd = CurrencyIso.USD;

        final LocalDate nearDate = LocalDate.of(2016, Month.MARCH, 1);
        final LocalDate farDate = nearDate.plusDays(31);
        final LocalDate priceDate = nearDate.plusDays(1);

        final FixedCouponBond mockBond = mock(FixedCouponBond.class);
        final BondPrice<USD> mockBondPrice = mock(BondPrice.class);
        final Money<USD> dirtyValue = DecimalMoney.valueOf(usd, 12_157_315.07);
        when(mockBondPrice.dirtyValue()).thenReturn(dirtyValue);
        when(mockBondPricer.price(priceDate, mockBond, BondShifts.NONE, usd)).thenReturn(mockBondPrice);

        final RepoRate rate = new RepoInterestRate(new SimpleFixedInterestRate(new Percentage(0.885), ActualFixedAccrualFactor.ACT_360));
        final RepoDates dates = new TermRepoDates(nearDate.minusDays(1), nearDate, farDate);
        final BondRepo repo = new BondRepo("repo", rate, mockBond, dates);

        final RepoPrice<USD> repoPrice = testRepoPricer.price(priceDate, repo, usd);
        assertThat(repoPrice.cleanValue().doubleValue(), Matchers.closeTo(12_166_579.96, 1e-2));

    }

}
