package com.github.moaxcp.graphs;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FromToElementTest {

    FromToElement element = new FromToElement("from", "to"){};

    @Test
    void testConstructor() {
        assertThat(element.getFrom()).isEqualTo("from");
        assertThat(element.getTo()).isEqualTo("to");
    }

    @Test
    void testConstructorNullFrom() {
        assertThrows(NullPointerException.class, () -> new FromToElement(null, "to") {});
    }

    @Test
    void testConstructorNullTo() {
        assertThrows(NullPointerException.class, () -> new FromToElement("from", null){});
    }

    @Test
    void testSetFrom() {
        element.setFrom("A");
        assertThat(element).containsEntry("from", "A");
    }

    @Test
    void testSetFromNull() {
        assertThrows(NullPointerException.class, () -> element.setFrom(null));
    }

    @Test
    void testSetTo() {
        element.setTo("B");
        assertThat(element).containsEntry("to", "B");
    }

    @Test
    void testSetToNull() {
        assertThrows(NullPointerException.class, () -> element.setTo(null));
    }

    @Test
    void testPutFrom() {
        String old = (String) element.put("from", "A");
        assertThat(element.getFrom()).isEqualTo("A");
        assertThat(old).isEqualTo("from");
    }

    @Test
    void testPutFromNull() {
        assertThrows(NullPointerException.class, () -> element.put("from", null));
    }

    @Test
    void testPutTo() {
        String old = (String) element.put("to", "B");
        assertThat(element.getTo()).isEqualTo("B");
        assertThat(old).isEqualTo("to");
    }

    @Test
    void testPutToNull() {
        assertThrows(NullPointerException.class, () -> element.put("to", null));
    }

    @Test
    void testPutAllFrom() {
        var map = Map.of("from", "A");
        element.putAll(map);
        assertThat(element).containsExactly("from", "A", "to", "to");
    }

    @Test
    void testPutAllFromNull() {
        var map = new HashMap<String, Object>();
        map.put("from", null);
        assertThrows(NullPointerException.class, () -> element.putAll(map));
    }

    @Test
    void testPutAllTo() {
        var map = Map.of("to", "B");
        element.putAll(map);
        assertThat(element).containsExactly("from", "from", "to", "B");
    }

    @Test
    void testPutAllToNull() {
        var map = new HashMap<String, Object>();
        map.put("to", null);
        assertThrows(NullPointerException.class, () -> element.putAll(map));
    }

    @Test
    void testRemoveFrom() {
        assertThrows(IllegalArgumentException.class, () -> element.remove("from"));
    }

    @Test
    void testRemoveTo() {
        assertThrows(IllegalArgumentException.class, () -> element.remove("to"));
    }

    @Test
    void testReplaceAll() {
        element.put("key", "value");
        element.replaceAll((s, o) -> "value2");
        assertThat(element).containsExactly("from", "value2", "to", "value2", "key", "value2");
    }
}
