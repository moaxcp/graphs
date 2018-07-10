package com.github.moaxcp.graphs;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IdentifiedElementTest {

    IdentifiedElement element = new IdentifiedElement("id") {};

    @Test
    void testConstructor() {
        assertThat(element).containsExactly("id", "id");
    }

    @Test
    void testConstructorNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IdentifiedElement(null){});
    }

    @Test
    void testGetId() {
        assertThat(element.getId()).isEqualTo("id");
    }

    @Test
    void testSetId() {
        element.setId("a");
        assertThat(element).containsExactly("id", "a");
    }

    @Test
    void testPut() {
        element.put("key", "value");
        assertThat(element).containsExactly("id", "id", "key", "value");
    }

    @Test
    void testPutId() {
        element.put("id", "a");
        assertThat(element).containsExactly("id", "a");
    }

    @Test
    void testPutAll() {
        var attributes = new HashMap<String, Object>();
        attributes.put("key", "value");
        element.putAll(attributes);
        assertThat(element).containsExactly("id", "id", "key", "value");
    }

    @Test
    void testPutAllWithId() {
        var attributes = new HashMap<String, Object>();
        attributes.put("id", "A");
        attributes.put("key", "value");
        element.putAll(attributes);
        assertThat(element).containsExactly("id", "A", "key", "value");
    }

    @Test
    void testRemoveId() {
        assertThrows(IllegalArgumentException.class, () -> element.remove("id"));
    }

    @Test
    void testReplaceAll() {
        element.put("key", "value");
        element.replaceAll((s, o) -> "value2");
        assertThat(element).containsExactly("id", "value2", "key", "value2");
    }
}
