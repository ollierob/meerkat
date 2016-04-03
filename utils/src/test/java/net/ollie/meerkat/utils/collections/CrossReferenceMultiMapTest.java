package net.ollie.meerkat.utils.collections;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;

import static net.ollie.meerkat.utils.collections.Sets.asSet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class CrossReferenceMultiMapTest {

    @Test
    public void testAllOf() {

        final CrossReferenceMultiMap<String, String> map = new CrossReferenceMultiMap<>();
        map.put("a", "x");
        map.put("b", "x");
        map.put("c", "x");
        map.put("d", "y");
        map.put("e", "x");
        map.put("e", "y");

        assertThat(map.allOf("a"), is(singleton("x")));
        assertThat(map.allOf("a", "b", "e"), is(singleton("x")));
        assertThat(map.allOf("a", "b", "c", "e"), is(singleton("x")));
        assertThat(map.allOf("a", "b", "c", "d", "e"), is(emptySet()));

        assertThat(map.allOf("d"), is(singleton("y")));
        assertThat(map.allOf("d", "e"), is(singleton("y")));

        assertThat(map.allOf("e"), is(asSet("x", "y")));

    }

}
