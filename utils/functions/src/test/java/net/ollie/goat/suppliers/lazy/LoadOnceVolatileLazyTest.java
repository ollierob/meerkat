package net.ollie.goat.suppliers.lazy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.function.Supplier;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class LoadOnceVolatileLazyTest {

    @Mock
    private Supplier<Object> mockSupplier;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldLoadOnce() {
        final Object object = new Object();
        when(mockSupplier.get()).thenReturn(object);
        final LoadOnceVolatileLazy<Object> lazy = new LoadOnceVolatileLazy<>(mockSupplier);
        assertThat(lazy.get(), is(object));
        assertThat(lazy.get(), is(object));
        assertThat(lazy.get(), is(object));
        assertThat(lazy.get(), is(object));
        assertThat(lazy.get(), is(object));
        verify(mockSupplier, times(1)).get();
    }

}
