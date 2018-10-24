package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.event.PropertyAddedGraphEvent;
import com.github.moaxcp.graphs.event.PropertyRemovedGraphEvent;
import com.github.moaxcp.graphs.event.PropertyUpdatedGraphEvent;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AllElementsTest {

    static Stream<Element> elements() {
        Graph graph = new Graph("graph");
        return Stream.of(
                new TestElement(EventBus.getDefault()),
                new TestInheritingElement(new HashMap<>(), EventBus.getDefault()),
                new TestIdentifiedInheritingElement("id", new HashMap<>(), EventBus.getDefault()),
                new TestOptionallyIdentifiedElement(EventBus.getDefault()),
                new TestOptionallyIdentifiedInheritingElement(new HashMap<>(), EventBus.getDefault()),
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
        assertThat(element.getLocal()).containsEntry("key", "value");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testGetProperty(Element element) {
        element.setProperty("key", "value");
        assertThat(element.getProperty("key")).hasValue("value");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testGetPropertyNull(Element element) {
        element.setProperty("key", "value");
        Throwable thrown = assertThrows(NullPointerException.class, () -> element.getProperty(null));
        assertThat(thrown).hasMessageThat().isEqualTo("name must not be null.");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetProperty(Element element) {
        element.setProperty("key", "value");
        assertThat(element.getProperty("key")).hasValue("value");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetPropertyNullName(Element element) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> element.setProperty(null, null));
        assertThat(thrown).hasMessageThat().isEqualTo("name must not be null.");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetPropertyNullValue(Element element) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> element.setProperty("", null));
        assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetPropertyEmptyName(Element element) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> element.setProperty("", "value"));
        assertThat(thrown).hasMessageThat().isEqualTo("name must not be empty.");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetPropertyAddEvent(Element element) {
        var handler = new TestHandler();
        EventBus.getDefault().register(handler);
        element.setProperty("key", "value");
        assertThat(handler.event).isInstanceOf(PropertyAddedGraphEvent.class);
        assertThat(((PropertyAddedGraphEvent) handler.event).getName()).isEqualTo("key");
        assertThat(((PropertyAddedGraphEvent) handler.event).getValue()).isEqualTo("value");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSetPropertyUpdatedEvent(Element element) {
        element.setProperty("key", "not value");
        var handler = new TestHandler();
        EventBus.getDefault().register(handler);
        element.setProperty("key", "value");
        assertThat(handler.event).isInstanceOf(PropertyUpdatedGraphEvent.class);
        assertThat(((PropertyUpdatedGraphEvent) handler.event).getName()).isEqualTo("key");
        assertThat(((PropertyUpdatedGraphEvent) handler.event).getValue()).isEqualTo("value");
        assertThat(((PropertyUpdatedGraphEvent) handler.event).getOldValue()).isEqualTo("not value");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testAddProperties(Element element) {
        var properties = new HashMap<String, Object>();
        properties.put("key1", "value1");
        properties.put("key2", "value2");
        element.addProperties(properties);
        assertThat(element.getLocal()).containsEntry("key1", "value1");
        assertThat(element.getLocal()).containsEntry("key2", "value2");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testWithProperty(Element element) {
        Element result = element.withProperty("key", "value");
        assertThat(element.getProperty("key")).hasValue("value");
        assertThat(result).isSameAs(element);
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testRemoveProperty(Element element) {
        element.setProperty("key", "value");
        element.removeProperty("key");
        assertThat(element.getProperty("key")).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testRemovePropertyEvent(Element element) {
        element.setProperty("key", "value");
        var handler = new TestHandler();
        EventBus.getDefault().register(handler);
        element.removeProperty("key");
        assertThat(handler.event).isInstanceOf(PropertyRemovedGraphEvent.class);
        assertThat(((PropertyRemovedGraphEvent) handler.event).getName()).isEqualTo("key");
        assertThat(((PropertyRemovedGraphEvent) handler.event).getValue()).isEqualTo("value");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testRemovePropertyNullName(Element element) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> element.removeProperty("key"));
        assertThat(thrown).hasMessageThat().isEqualTo("element does not contain property named 'key'.");
    }
}
