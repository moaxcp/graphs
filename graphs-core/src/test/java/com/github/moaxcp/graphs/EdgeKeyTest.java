package com.github.moaxcp.graphs;

import static com.google.common.truth.Truth.assertThat;
import nl.jqno.equalsverifier.*;
import org.junit.jupiter.api.Test;

public class EdgeKeyTest {
    @Test
    void directedEquals() {
        EqualsVerifier.forClass(DirectedEdgeKey.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }

    @Test
    void undirectedEqualsNull() {
        var key = new UndirectedEdgeKey<>("A", "B");
        assertThat(key.equals(null)).isFalse();
    }

    @Test
    void undirectedEqualInstance() {
        var key = new UndirectedEdgeKey<>("A", "B");
        assertThat(key.equals(key)).isTrue();
    }

    @Test
    void undirectedDifferentClass() {
        var key = new UndirectedEdgeKey<>("A", "B");
        assertThat(key.equals("string")).isFalse();
    }

    @Test
    void undirectedNoneEqual() {
        var key1 = new UndirectedEdgeKey<>("A", "B");
        var key2 = new UndirectedEdgeKey<>("C", "D");
        assertThat(key1.equals(key2)).isFalse();
    }

    @Test
    void undirectedFromEqual() {
        var key1 = new UndirectedEdgeKey<>("A", "B");
        var key2 = new UndirectedEdgeKey<>("A", "D");
        assertThat(key1.equals(key2)).isFalse();
    }

    @Test
    void undirectedFromEqualsTo() {
        var key1 = new UndirectedEdgeKey<>("A", "B");
        var key2 = new UndirectedEdgeKey<>("C", "A");
        assertThat(key1.equals(key2)).isFalse();
    }

    @Test
    void undirectedToEqual() {
        var key1 = new UndirectedEdgeKey<>("A", "B");
        var key2 = new UndirectedEdgeKey<>("C", "B");
        assertThat(key1.equals(key2)).isFalse();
    }

    @Test
    void undirectedToEqualsFrom() {
        var key1 = new UndirectedEdgeKey<>("A", "B");
        var key2 = new UndirectedEdgeKey<>("B", "D");
        assertThat(key1.equals(key2)).isFalse();
    }

    @Test
    void undirectedAllEqual() {
        var key1 = new UndirectedEdgeKey<>("A", "B");
        var key2 = new UndirectedEdgeKey<>("A", "B");
        assertThat(key1.equals(key2)).isTrue();
    }

    @Test
    void undirectedSwitchedEqual() {
        var key1 = new UndirectedEdgeKey<>("A", "B");
        var key2 = new UndirectedEdgeKey<>("B", "A");
        assertThat(key1.equals(key2)).isTrue();
    }
}
