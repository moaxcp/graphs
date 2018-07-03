package com.github.moaxcp.graphs;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IdentifiedElementTest {
    @Test
    void testConstructor() {
        var element = new IdentifiedElement("id"){};
        assertThat(element).containsExactly("id", "id");
    }

    @Test
    void testConstructorNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IdentifiedElement(null){});
    }

    @Test
    void testGetId() {
        var element = new IdentifiedElement("id"){};
        assertThat(element.getId()).isEqualTo("id");
    }

    @Test
    void testSetId() {
        var element = new IdentifiedElement("id"){};
        element.setId("a");
        assertThat(element).containsExactly("id", "a");
    }

    @Test
    void testPut() {
        var element = new IdentifiedElement("id"){};
        element.put("key", "value");
        assertThat(element).containsExactly("id", "id", "key", "value");
    }

    @Test
    void testPutId() {
        var element = new IdentifiedElement("id"){};
        element.put("id", "a");
        assertThat(element).containsExactly("id", "a");
    }
}
