package net.meerkat.calculate.price.repo;

//package net.meerkat.calculate.price.repo;
//
//import net.meerkat.calculate.price.interest.repo.NativeBondRepoPricer;
//import net.meerkat.calculate.price.interest.repo.RepoPrice;
//
//import java.time.LocalDate;
//import java.time.Month;
//
//import org.hamcrest.Matchers;
//import static org.junit.Assert.assertThat;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.experimental.categories.Category;
//import org.mockito.Mock;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import org.mockito.MockitoAnnotations;
//
//import net.meerkat.IntegrationTest;
//import net.meerkat.calculate.price.bond.BondPrice;
//import net.meerkat.calculate.price.bond.BondPricer;
//import net.meerkat.calculate.price.bond.BondShifts;
//import net.meerkat.identifier.currency.CurrencyIso;
//import net.meerkat.identifier.currency.USD;
//import net.meerkat.identifier.security.SecurityIds;
//import net.ollie.goat.numeric.percentage.DecimalPercentage;
//import net.meerkat.money.interest.fixed.SimpleFixedInterestRate;
//import net.meerkat.money.DecimalMoney;
//import net.meerkat.money.Money;
//import net.meerkat.security.bond.FixedCouponBond;
//import net.meerkat.security.interest.repo.BondRepo;
//import net.meerkat.security.interest.repo.dates.RepoDates;
//import net.meerkat.security.interest.repo.dates.TermRepoDates;
//import net.meerkat.security.interest.repo.rate.RepoInterestRate;
//import net.meerkat.security.interest.repo.rate.RepoRate;
//import net.ollie.goat.temporal.date.count.ActualFixedAccrualFactor;
//
///**
// *
// * @author ollie
// */
//@SuppressWarnings("unchecked")
//public class NativeBondRepoPricerTest {
//
//    @Mock
//    private BondPricer mockBondPricer;
//    private NativeBondRepoPricer testRepoPricer;
//
//    @Before
//    public void before() {
//        MockitoAnnotations.initMocks(this);
//        testRepoPricer = new NativeBondRepoPricer(mockBondPricer);
//    }
//
//    @Test
//    @Category(IntegrationTest.class)
//    public void testExample() {
//
//        final USD usd = CurrencyIso.USD;
//
//        final LocalDate nearDate = LocalDate.of(2016, Month.MARCH, 1);
//        final LocalDate farDate = nearDate.plusDays(31);
//        final LocalDate priceDate = nearDate.plusDays(1);
//
//        final FixedCouponBond mockBond = mock(FixedCouponBond.class);
//        final BondPrice.Shiftable<USD> mockBondPrice = mock(BondPrice.Shiftable.class);
//        final Money<USD> dirtyValue = DecimalMoney.valueOf(usd, 12_157_315.07);
//        when(mockBondPrice.dirty()).thenReturn(dirtyValue);
//        when(mockBondPricer.price(priceDate, mockBond, usd, BondShifts.none())).thenReturn(mockBondPrice);
//
//        final RepoRate rate = new RepoInterestRate(new SimpleFixedInterestRate(new DecimalPercentage(0.885), ActualFixedAccrualFactor.ACT_360));
//        final RepoDates dates = new TermRepoDates(nearDate.minusDays(1), nearDate, farDate);
//        final BondRepo repo = new BondRepo("repo", mock(SecurityIds.class), rate, mockBond, dates, null);
//
//        final RepoPrice<USD> repoPrice = testRepoPricer.price(priceDate, repo, usd);
//        assertThat(repoPrice.clean().doubleValue(), Matchers.closeTo(12_166_579.96, 1e-2));
//
//    }
//
//}
