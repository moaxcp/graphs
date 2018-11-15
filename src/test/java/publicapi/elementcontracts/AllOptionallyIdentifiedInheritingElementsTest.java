package publicapi.elementcontracts;

import static com.google.common.truth.Truth8.*;
import com.github.moaxcp.graphs.element.*;
import com.github.moaxcp.graphs.greenrobot.*;
import com.github.moaxcp.graphs.greenrobot.DirectedGraph.*;
import com.github.moaxcp.graphs.greenrobot.UndirectedGraph.*;
import java.util.*;
import java.util.stream.*;
import org.greenrobot.eventbus.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import stubs.*;

public class AllOptionallyIdentifiedInheritingElementsTest {
    static Map<String, Object> inherited = new HashMap<>();

    static Stream<OptionallyIdentifiedInheritingElement> elements() {
        return Stream.of(
                new TestOptionallyIdentifiedInheritingElement(inherited, EventBus.getDefault()),
                (UndirectedEdge) new UndirectedGraph().edge("from", "to"),
                (DirectedEdge) new DirectedGraph().edge("from", "to"));
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
