package com.github.moaxcp.graphs;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;

import static com.google.common.truth.Truth.assertThat;

class ElementTest {

    Element element = new Element() {};
    @Test
    void testCosntructor() {
        assertThat(element.getAttributes()).isEmpty();
    }

    @Test
    void testGetAttributes() {
        element.put("key", "value");
        var attributes = element.getAttributes();
        assertThat(attributes).containsExactly("key", "value");
    }

    @Test
    void testSize() {
        element.put("key", "value");
        assertThat(element.size()).isEqualTo(1);
    }

    @Test
    void testIsEmpty() {
        assertThat(element).isEmpty();
    }

    @Test
    void testKeySet() {
        element.put("key", "value");
        assertThat(element.keySet()).containsExactly("key");
    }

    @Test
    void testValues() {
        element.put("key", "value");
        assertThat(element.values()).containsExactly("value");
    }

    @Test
    void testEntrySet() {
        element.put("key", "value");
        assertThat(element.entrySet()).containsExactly(new AbstractMap.SimpleEntry<>("key", "value"));
    }
}
