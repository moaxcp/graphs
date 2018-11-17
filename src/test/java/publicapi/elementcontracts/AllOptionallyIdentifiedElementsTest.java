package publicapi.elementcontracts;

import com.github.moaxcp.graphs.greenrobot.DirectedGraph;
import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;
import com.github.moaxcp.graphs.greenrobot.element.OptionallyIdentifiedElement;
import stubs.TestOptionallyIdentifiedElement;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

public class AllOptionallyIdentifiedElementsTest {

    static Stream<OptionallyIdentifiedElement> elements() {
        return Stream.of(
                new TestOptionallyIdentifiedElement(EventBus.getDefault()),
                new UndirectedGraph(),
                new DirectedGraph());
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testGetIdNull(OptionallyIdentifiedElement element) {
        assertThat(element.getId()).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetId(OptionallyIdentifiedElement element) {
        element.setId("id");
        assertThat(element.getId()).hasValue("id");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetIdNull(OptionallyIdentifiedElement element) {
        element.setId(null);
        assertThat(element.getId()).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetIdFromValueToNull(OptionallyIdentifiedElement element) {
        element.setId("id");
        element.setId(null);
        assertThat(element.getId()).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetPropertyId(OptionallyIdentifiedElement element) {
        element.setProperty("id", "id");
        assertThat(element.getId()).hasValue("id");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetProperty(OptionallyIdentifiedElement element) {
        element.setProperty("key", "value");
        assertThat(element.getProperty("key")).hasValue("value");
    }
}
