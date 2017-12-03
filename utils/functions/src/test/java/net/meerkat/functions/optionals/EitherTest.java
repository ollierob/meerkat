package net.meerkat.functions.optionals;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Consumer;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class EitherTest {

    @Test
    public void testLeft_Left() {
        final Object value = new Object();
        this.testLeft(Either.left(value), value);
    }

    @Test
    public void testLeft_Either() {
        final Object value = new Object();
        this.testLeft(Either.either(() -> value, () -> new Object()), value);
    }

    private void testLeft(final Either<Object, Object> either, final Object value) {

        assertThat(either.left(), is(Optional.of(value)));
        assertThat(either.right(), is(Optional.empty()));

        final Consumer<Object> mockLeftConsumer = mock(Consumer.class);
        final Consumer<Object> mockRightConsumer = mock(Consumer.class);
        either.consume(mockLeftConsumer, mockRightConsumer);
        verify(mockLeftConsumer, times(1)).accept(eq(value));
        verify(mockLeftConsumer, times(0)).accept(any(Object.class));
        verifyNoMoreInteractions(mockLeftConsumer, mockRightConsumer);

    }

}
