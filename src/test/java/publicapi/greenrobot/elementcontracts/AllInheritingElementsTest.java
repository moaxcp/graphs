package publicapi.greenrobot.elementcontracts;

import static com.google.common.truth.Truth8.*;
import static org.junit.jupiter.api.Assertions.*;
import com.github.moaxcp.graphs.greenrobot.element.*;
import com.github.moaxcp.graphs.greenrobot.*;
import com.github.moaxcp.graphs.greenrobot.UndirectedEventGraph.*;
import java.util.*;
import java.util.stream.*;
import org.greenrobot.eventbus.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import testframework.stubs.*;

public class AllInheritingElementsTest {
    static Map<String, Object> inherited = new HashMap<>();
    static UndirectedEventGraph graph = new UndirectedEventGraph("graph");
    static Stream<InheritingElement> elements() {
        return Stream.of(
                new TestInheritingElement(inherited, EventBus.getDefault()),
                new TestIdentifiedInheritingElement("id", inherited, EventBus.getDefault()),
                new TestOptionallyIdentifiedInheritingElement(inherited, EventBus.getDefault()),
                (UndirectedVertex) graph.vertex("a"),
                (UndirectedEdge) graph.edge("from", "to"));
    }

    @BeforeAll
    static void setup() {
        inherited.put("inherited", "inheritedValue");
        graph.setNodeProperty("inherited", "inheritedValue");
        graph.setEdgeProperty("inherited", "inheritedValue");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testInheritedIsUnmodifiable(InheritingElement element) {
        assertThrows(UnsupportedOperationException.class, () -> element.inherited().put("key", "value"));
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testGetLocalProperty(InheritingElement element) {
        element.setProperty("key", "value");
        assertThat(element.getProperty("key")).hasValue("value");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testGetInheritedProperty(InheritingElement element) {
        assertThat(element.getProperty("inherited")).hasValue("inheritedValue");
    }
}
