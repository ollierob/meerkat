package net.meerkat.functions.collectors;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static net.meerkat.functions.collectors.PairCollectors.pairs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

/**
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class PairCollectorsTest {

    @Test
    public void shouldCollectPairsToList() {
        final List<Integer> integers = Arrays.asList(2, 3, 5, 7);
        final List<Integer> multiplied = integers.stream().collect(pairs((a, b) -> a * b));
        assertThat(multiplied, contains(6, 15, 35));
    }

}
