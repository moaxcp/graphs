package com.github.moaxcp.graphs;

import de.muspellheim.eventbus.EventBus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AllInheritingElementsTest {
    static Map<String, Object> inherited = new HashMap<>();
    static Graph graph = new Graph("graph");
    static Stream<InheritingElement> elements() {
        return Stream.of(
                new TestInheritingElement(inherited, EventBus.getDefault()),
                new TestIdentifiedInheritingElement("id", inherited, EventBus.getDefault()),
                new TestOptionallyIdentifiedInheritingElement("id", inherited, EventBus.getDefault()),
                graph.vertex("a"),
                graph.edge("from", "to"));
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
        assertThat(element.getProperty("key")).isEqualTo("value");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testGetInheritedProperty(InheritingElement element) {
        assertThat(element.getProperty("inherited")).isEqualTo("inheritedValue");
    }
}
