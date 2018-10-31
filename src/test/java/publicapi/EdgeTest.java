package publicapi;

import com.github.moaxcp.graphs.Graph;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.github.moaxcp.graphs.Truth.assertThat;

public class EdgeTest {
    Graph graph = new Graph("graph");

    @Test
    void setId() {
        var edge = graph.edge("A", "B");
        edge.setId("id");
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id");
        assertThat(graph).hasEdge("id").isSameAs(edge);
    }

    @Test
    void id() {
        var edge = graph.edge("A", "B").id("id");
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id");
        assertThat(graph).hasEdge("id").isSameAs(edge);
    }

    @Test
    void changeId() {
        var edge = graph.edge("A", "B").id("id");
        edge.setId("id2");
        assertThat(graph).hasEdge("id2").isSameAs(edge);
        assertThat(graph).hasEdge("A", "B").hasIdThat().hasValue("id2");
        assertThat(graph).hasEdge("id2").hasIdThat().hasValue("id2");
        assertThat(graph).hasNoEdge("id");
    }

    @Test
    void setIdNullRemovesId() {
        var edge = graph.edge("from", "to").id("id");
        edge.setId(null);
        assertThat(edge).hasNoId();
        assertThat(graph).hasNoEdge("id");
    }

    @Test
    void removeIdProperty() {
        var edge = graph.edge("from", "to").id("id");
        edge.removeProperty("id");
        assertThat(edge).hasNoId();
        assertThat(graph).hasNoEdge("id");
    }

    @Test
    void setFrom() {
        var edge = graph.edge("A", "B");
        edge.setFrom("C");
        assertThat(graph).hasVertex("C");
        assertThat(edge).hasFromThat().isEqualTo("C");
    }

    @Test
    void setTo() {
        var edge = graph.edge("A", "B");
        edge.setTo("C");
        assertThat(graph).hasVertex("C");
        assertThat(edge).hasToThat().isEqualTo("C");
    }

    @Test
    void from() {
        var edge = graph.edge("A", "B");
        var vertex = graph.findVertex("A").get();
        assertThat(edge.from()).isEqualTo(vertex);
    }

    @Test
    void to() {
        var edge = graph.edge("A", "B");
        var vertex = graph.findVertex("B").get();
        assertThat(edge.to()).isEqualTo(vertex);
    }

    @Test
    void setProperty() {
        var edge = graph.edge("A", "B");
        edge.setProperty("key", "value");
        assertThat(edge).hasPropertyThat("key").hasValue("value");
    }

    @Test
    void setPropertyFrom() {
        var edge = graph.edge("A", "B");
        edge.setProperty("from", "C");
        assertThat(edge).hasFromThat().isEqualTo("C");
    }

    @Test
    void testEqualsNull() {
        var edge = graph.edge("from", "to");
        assertThat(edge).isNotEqualTo(null);
    }

    @Test
    void testEqualsReflexive() {
        var edge = graph.edge("from", "to");
        assertThat(edge).isEqualTo(edge);
    }

    @Test
    void testEqualsSymmetric() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new Graph().edge("from", "to"); //todo graph should be included in equals
        assertEquals(edge1, edge2);
        assertEquals(edge2, edge1);
    }

    @Test
    void testEqualsTransitive() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new Graph().edge("from", "to");
        var edge3 = new Graph().edge("from", "to");
        assertEquals(edge1, edge2);
        assertEquals(edge2, edge3);
        assertEquals(edge1, edge3);
    }

    @Test
    void testConsistent() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new Graph().edge("from", "to");
        assertEquals(edge1, edge2);
        assertEquals(edge1, edge2);
        assertEquals(edge1, edge2);
    }

    @Test
    void testNonDirectional() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new Graph().edge("to", "from");
        assertEquals(edge1, edge2);
    }

    @Test
    void testAlmostEqualTo() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new Graph().edge("from", "from");
        assertNotEquals(edge1, edge2);
    }

    @Test
    void testAlmostEqualFrom() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new Graph().edge("to", "to");
        assertNotEquals(edge1, edge2);
    }

    @Test
    void testHashCodeConsistent() {
        var edge = graph.edge("from", "to");
        int hashCode = edge.hashCode();
        assertThat(edge.hashCode()).isEqualTo(hashCode);
        assertThat(edge.hashCode()).isEqualTo(hashCode);
    }

    @Test
    void testHashCodeForEqualEdges() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new Graph().edge("from", "to");
        assertEquals(edge1, edge2);
        assertThat(edge1.hashCode()).isEqualTo(edge2.hashCode());
    }
}
