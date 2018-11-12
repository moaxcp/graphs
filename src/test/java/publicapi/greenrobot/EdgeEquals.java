package publicapi.greenrobot;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;
import com.github.moaxcp.graphs.Truth;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EdgeEquals {
    private UndirectedGraph graph = new UndirectedGraph();

    @Test
    void testEqualsNull() {
        var edge = graph.edge("from", "to");
        Truth.assertThat(edge).isNotEqualTo(null);
    }

    @Test
    void testEqualsReflexive() {
        var edge = graph.edge("from", "to");
        Truth.assertThat(edge).isEqualTo(edge);
    }

    @Test
    void testEqualsSymmetric() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new UndirectedGraph().edge("from", "to"); //todo graph should be included in equals
        assertEquals(edge1, edge2);
        assertEquals(edge2, edge1);
    }

    @Test
    void testEqualsTransitive() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new UndirectedGraph().edge("from", "to");
        var edge3 = new UndirectedGraph().edge("from", "to");
        assertEquals(edge1, edge2);
        assertEquals(edge2, edge3);
        assertEquals(edge1, edge3);
    }

    @Test
    void testConsistent() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new UndirectedGraph().edge("from", "to");
        assertEquals(edge1, edge2);
        assertEquals(edge1, edge2);
        assertEquals(edge1, edge2);
    }

    @Test
    void testNonDirectional() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new UndirectedGraph().edge("to", "from");
        assertEquals(edge1, edge2);
    }

    @Test
    void testAlmostEqualTo() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new UndirectedGraph().edge("from", "from");
        assertNotEquals(edge1, edge2);
    }

    @Test
    void testAlmostEqualFrom() {
        var edge1 = graph.edge("from", "to");
        var edge2 = new UndirectedGraph().edge("to", "to");
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
        var edge2 = new UndirectedGraph().edge("from", "to");
        assertEquals(edge1, edge2);
        assertThat(edge1.hashCode()).isEqualTo(edge2.hashCode());
    }
}
