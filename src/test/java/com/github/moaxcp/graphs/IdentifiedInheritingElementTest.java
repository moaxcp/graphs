package com.github.moaxcp.graphs;

import de.muspellheim.eventbus.EventBus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IdentifiedInheritingElementTest {

    IdentifiedInheritingElement element = new TestIdentifiedInheritingElement("id", new HashMap<>(), EventBus.getDefault());

    @Test
    void testConstructor() {
        assertThat(element.getId()).isEqualTo("id");
        assertThat(element.getLocal()).containsExactly("id", "id");
    }

    @Test
    void testConstructorNullPointerException() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new TestIdentifiedInheritingElement(null, new HashMap<>(), EventBus.getDefault()));
        assertThat(thrown).hasMessageThat().isEqualTo("id must not be null.");
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
    void testSetIdNull() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> element.setId(null));
        assertThat(thrown).hasMessageThat().isEqualTo("id must not be null.");
    }

    @Test
    void testSetProperty() {
        element.setProperty("key", "value");
        assertThat(element.getLocal()).containsExactly("id", "id", "key", "value");
    }

    @Test
    void testSetPropertyId() {
        element.setProperty("id", "A");
        assertThat(element.getId()).isEqualTo("A");
    }

    @Test
    void testSetPropertyIdNotString() {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> element.setProperty("id", UUID.randomUUID()));
        assertThat(thrown).hasMessageThat().isEqualTo("id must be set to a String object.");
    }

    @Test
    void testRemoveProperty() {
        element.setProperty("key", "value");
        assertThat(element.getProperty("key")).isEqualTo("value");
        element.removeProperty("key");
        assertThat(element.getLocal()).containsExactly("id", "id");
    }

    @Test
    void testRemovePropertyId() {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> element.removeProperty("id"));
        assertThat(thrown).hasMessageThat().isEqualTo("id can not be removed.");
    }
}
