package com.github.moaxcp.graphs;

import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AllIdentifiedInheritingElementsTest {

    static Map<String, Object> inherited = new HashMap<>();
    static Graph graph = new Graph("graph");
    static Stream<InheritingElement> elements() {
        return Stream.of(
                new TestIdentifiedInheritingElement("id", inherited, EventBus.getDefault()),
                graph.vertex("id"));
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testConstructor(IdentifiedInheritingElement element) {
        assertThat(element.getId()).isEqualTo("id");
        assertThat(element.getLocal()).containsExactly("id", "id");
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
        assertThat(element.getLocal()).containsExactly("id", "a");
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
        assertThat(element.getLocal()).containsExactly("id", "id", "key", "value");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetPropertyId(IdentifiedInheritingElement element) {
        element.setProperty("id", "A");
        assertThat(element.getId()).isEqualTo("A");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetPropertyIdNotString(IdentifiedInheritingElement element) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> element.setProperty("id", UUID.randomUUID()));
        assertThat(thrown).hasMessageThat().isEqualTo("id must be set to a String object.");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testRemoveProperty(IdentifiedInheritingElement element) {
        element.setProperty("key", "value");
        assertThat(element.getProperty("key")).isEqualTo("value");
        element.removeProperty("key");
        assertThat(element.getLocal()).containsExactly("id", "id");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testRemovePropertyId(IdentifiedInheritingElement element) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> element.removeProperty("id"));
        assertThat(thrown).hasMessageThat().isEqualTo("id can not be removed.");
    }
}
