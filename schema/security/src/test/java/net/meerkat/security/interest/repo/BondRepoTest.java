package net.meerkat.security.interest.repo;

import net.meerkat.instrument.interest.repo.BondRepo;
import net.meerkat.money.Money;
import net.ollie.goat.numeric.percentage.DecimalPercentage;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.instrument.interest.repo.dates.RepoDates;
import net.meerkat.instrument.interest.repo.rate.RepoRate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author ollie
 */
public class BondRepoTest {

    @Mock
    private RepoRate mockRate;
    @Mock
    private Bond mockBond;
    @Mock
    private RepoDates mockDates;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Ignore
    public void testHaircut_Null() {

        final InstrumentIds mockIds = mock(InstrumentIds.class);
        final BondRepo repo = new BondRepo("name", mockIds, mockRate, mockBond, mockDates, null);
        assertThat(repo.haircut(), is(DecimalPercentage.ZERO_PERCENT));

        final Money mockPar = mock(Money.class);
        when(mockBond.par()).thenReturn(mockPar);
        
        when(mockPar.times(DecimalPercentage.ONE_HUNDRED_PERCENT)).thenReturn(mockPar);
        
        assertThat(repo.principal(), is(mockPar));

    }

}
