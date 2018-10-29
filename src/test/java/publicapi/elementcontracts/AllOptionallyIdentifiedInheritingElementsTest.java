package publicapi.elementcontracts;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.OptionallyIdentifiedInheritingElement;
import stubs.TestOptionallyIdentifiedInheritingElement;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

public class AllOptionallyIdentifiedInheritingElementsTest {
    static Map<String, Object> inherited = new HashMap<>();

    static Stream<OptionallyIdentifiedInheritingElement> elements() {
        return Stream.of(
                new TestOptionallyIdentifiedInheritingElement(inherited, EventBus.getDefault()),
                new Graph().edge("from", "to"));
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testGetIdNull(OptionallyIdentifiedInheritingElement element) {
        assertThat(element.getId()).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetId(OptionallyIdentifiedInheritingElement element) {
        element.setId("id");
        assertThat(element.getId()).hasValue("id");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetIdNull(OptionallyIdentifiedInheritingElement element) {
        element.setId(null);
        assertThat(element.getId()).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetIdFromValueToNull(OptionallyIdentifiedInheritingElement element) {
        element.setId("id");
        element.setId(null);
        assertThat(element.getId()).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetPropertyId(OptionallyIdentifiedInheritingElement element) {
        element.setProperty("id", "id");
        assertThat(element.getId()).hasValue("id");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetProperty(OptionallyIdentifiedInheritingElement element) {
        element.setProperty("key", "value");
        assertThat(element.getProperty("key")).hasValue("value");
    }
}
