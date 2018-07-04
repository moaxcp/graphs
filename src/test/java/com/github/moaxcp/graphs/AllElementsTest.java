package com.github.moaxcp.graphs;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class AllElementsTest {

    static Stream<Element> elements() {
        Graph<String> graph = new Graph<>("graph");
        return Stream.of(new Element() {}, new IdentifiedElement<>("id") {}, graph.new Vertex("a"), graph.new Edge("from", "to"), new Graph<>("id"));
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testGetAttributes(Element element) {
        element.put("key", "value");
        var attributes = element.getAttributes();
        assertThat(attributes).containsEntry("key", "value");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testSize(Element element) {
        element.put("key", "value");
        assertThat(element.size()).isAtLeast(1);
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testGetAttributesUnmodifiable(Element element) {
        var attributes = element.getAttributes();
        assertThrows(UnsupportedOperationException.class, () -> attributes.put("key", "value"));
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testContainsKey(Element element) {
        element.put("key", "value");
        assertThat(element.containsKey("key")).isTrue();
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testContainsValue(Element element) {
        element.put("key", "value");
        assertThat(element.containsValue("value")).isTrue();
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testGet(Element element) {
        element.put("key", "value");
        assertThat(element.get("key")).isEqualTo("value");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testRemove(Element element) {
        element.put("key", "value");
        assertThat(element.remove("key")).isEqualTo("value");
        assertThat(element).doesNotContainEntry("key", "value");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testPutAll(Element element) {
        var map = new HashMap<String, String>();
        map.put("key", "value");
        element.putAll(map);
        assertThat(element).containsEntry("key", "value");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testClear(Element element) {
        element.put("key", "value");
        assertThat(element).containsEntry("key", "value");
        element.clear();
        assertThat(element).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testKeySet(Element element) {
        element.put("key", "value");
        assertThat(element.keySet()).contains("key");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testValues(Element element) {
        element.put("key", "value");
        assertThat(element.values()).contains("value");
    }

    @ParameterizedTest
    @MethodSource("elements")
    void testEntrySet(Element element) {
        element.put("key", "value");
        assertThat(element.entrySet()).contains(new AbstractMap.SimpleEntry<>("key", "value"));
    }
}
