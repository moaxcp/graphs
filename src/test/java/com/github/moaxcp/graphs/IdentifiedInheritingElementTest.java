package com.github.moaxcp.graphs;

import de.muspellheim.eventbus.EventBus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IdentifiedInheritingElementTest {

    IdentifiedInheritingElement element = new TestIdentifiedInheritingElement("id", new HashMap<>(), EventBus.getDefault());

    @Test
    void testConstructor() {
        assertThat(element.getLocal()).containsExactly("id", "id");
    }

    @Test
    void testConstructorNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TestIdentifiedInheritingElement(null, new HashMap<>(), EventBus.getDefault()));
    }

    @Test
    void testGetId() {
        assertThat(element.getId()).isEqualTo("id");
    }

    @Test
    void testSetId() {
        element.setId("a");
        assertThat(element.getLocal()).containsExactly("id", "a");
    }

    @Test
    void testPut() {
        element.setProperty("key", "value");
        assertThat(element.getLocal()).containsExactly("id", "id", "key", "value");
    }

    @Test
    void testPutId() {
        element.setProperty("id", "a");
        assertThat(element.getLocal()).containsExactly("id", "a");
    }
}
