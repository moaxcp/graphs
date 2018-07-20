package com.github.moaxcp.graphs;

import de.muspellheim.eventbus.EventBus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AllElementsTest {

    static Stream<Element> elements() {
        Graph graph = new Graph("graph");
        return Stream.of(
                new TestElement(EventBus.getDefault()),
                new TestIdentifiedInheritingElement("id", new HashMap<>(), EventBus.getDefault()),
                new TestOptionallyIdentifiedElement("id", EventBus.getDefault()),
                new TestOptionallyIdentifiedInheritingElement("id", new HashMap<>(), EventBus.getDefault()),
                graph.vertex("a"),
                graph.edge("from", "to"),
                new Graph("id"));
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testGetEventBus(Element element) {
        assertThat(element.getBus()).isSameAs(EventBus.getDefault());
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testGetLocal(Element element) {
        element.setProperty("key", "value");
        var attributes = element.getLocal();
        assertThat(attributes).containsEntry("key", "value");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testGetProperty(Element element) {
        element.setProperty("key", "value");
        assertThat(element.getProperty("key")).isEqualTo("value");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testGetPropertyNull(Element element) {
        element.setProperty("key", "value");
        assertThrows(NullPointerException.class, () -> element.getProperty(null), "name must not be empty.");
    }
}
