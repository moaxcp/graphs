package publicapi.greenrobot.elementcontracts;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import com.github.moaxcp.graphs.greenrobot.element.*;
import com.github.moaxcp.graphs.greenrobot.*;
import com.github.moaxcp.graphs.greenrobot.UndirectedEventGraph.*;
import java.util.*;
import java.util.stream.*;
import org.greenrobot.eventbus.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import testframework.stubs.*;

class AllIdentifiedInheritingElementsTest {

    static Map<String, Object> inherited = new HashMap<>();
    static UndirectedEventGraph graph = new UndirectedEventGraph("graph");
    static Stream<InheritingElement> elements() {
        return Stream.of(
                new TestIdentifiedInheritingElement("id", inherited, EventBus.getDefault()),
                (UndirectedVertex) graph.vertex("id"));
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testConstructor(IdentifiedInheritingElement element) {
        assertThat(element.getId()).isEqualTo("id");
        assertThat(element.local()).containsExactly("id", "id");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testConstructorNullPointerException(IdentifiedInheritingElement element) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new TestIdentifiedInheritingElement(null, new HashMap<>(), EventBus.getDefault()));
        assertThat(thrown).hasMessageThat().isEqualTo("id must not be null.");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testGetId(IdentifiedInheritingElement element) {
        assertThat(element.getId()).isEqualTo("id");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetId(IdentifiedInheritingElement element) {
        element.setId("a");
        assertThat(element.local()).containsExactly("id", "a");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetIdNull(IdentifiedInheritingElement element) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> element.setId(null));
        assertThat(thrown).hasMessageThat().isEqualTo("id must not be null.");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetProperty(IdentifiedInheritingElement element) {
        element.setProperty("key", "value");
        assertThat(element.local()).containsExactly("id", "id", "key", "value");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetPropertyId(IdentifiedInheritingElement element) {
        element.setProperty("id", "A");
        assertThat(element.getId()).isEqualTo("A");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testRemoveProperty(IdentifiedInheritingElement element) {
        element.setProperty("key", "value");
        assertThat(element.getProperty("key")).hasValue("value");
        element.removeProperty("key");
        assertThat(element.local()).containsExactly("id", "id");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testRemovePropertyId(IdentifiedInheritingElement element) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> element.removeProperty("id"));
        assertThat(thrown).hasMessageThat().isEqualTo("id can not be removed.");
    }
}
