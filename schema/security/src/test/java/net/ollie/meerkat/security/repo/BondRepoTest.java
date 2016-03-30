package net.ollie.meerkat.security.repo;

import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.Bond;
import net.ollie.meerkat.security.repo.dates.RepoDates;
import net.ollie.meerkat.security.repo.rate.RepoRate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
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
    public void testHaircut_Null() {

        final BondRepo repo = new BondRepo("name", mockRate, mockBond, mockDates, null);
        assertThat(repo.haircut(), is(Percentage.ZERO_PERCENT));

        final Money mockPar = mock(Money.class);
        when(mockBond.par()).thenReturn(mockPar);
        
        when(mockPar.times(Percentage.ONE_HUNDRED_PERCENT)).thenReturn(mockPar);
        
        assertThat(repo.principal(), is(mockPar));

    }

}
