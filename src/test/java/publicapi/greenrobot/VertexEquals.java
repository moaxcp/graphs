package publicapi.greenrobot;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.github.moaxcp.graphs.SimpleGraph;
import com.github.moaxcp.graphs.greenrobot.UndirectedEventGraph;
import org.junit.jupiter.api.Test;

public class VertexEquals {
    SimpleGraph graph = new UndirectedEventGraph();

    @Test
    void testEqualsNull() {
        var vertex = graph.vertex("A");
        assertNotEquals(vertex, null);
    }

    @Test
    void testEqualsReflexive() {
        var vertex = graph.vertex("A");
        assertEquals(vertex, vertex);
    }

    @Test
    void testEqualsSymmetric() {
        var vertex1 = graph.vertex("A");
        var vertex2 = new UndirectedEventGraph().vertex("A");
        assertEquals(vertex1, vertex2);
        assertEquals(vertex2, vertex1);
    }

    @Test
    void testEqualsTransitive() {
        var vertex1 = graph.vertex("A");
        var vertex2 = new UndirectedEventGraph().vertex("A");
        var vertex3 = new UndirectedEventGraph().vertex("A");
        assertEquals(vertex1, vertex2);
        assertEquals(vertex2, vertex3);
        assertEquals(vertex1, vertex3);
    }

    @Test
    void testEqualsConsistent() {
        var vertex1 = graph.vertex("A");
        var vertex2 = new UndirectedEventGraph().vertex("A");
        assertEquals(vertex1, vertex2);
        assertEquals(vertex1, vertex2);
        assertEquals(vertex1, vertex2);
    }

    @Test
    void testHashCodeConsistent() {
        var vertex = graph.vertex("A");
        int hashCode = vertex.hashCode();
        assertThat(vertex.hashCode()).isEqualTo(hashCode);
        assertThat(vertex.hashCode()).isEqualTo(hashCode);
    }

    @Test
    void testHashCodeForEqualVertices() {
        var vertex1 = graph.vertex("A");
        var vertex2 = new UndirectedEventGraph().vertex("A");
        assertEquals(vertex1, vertex2);
        assertThat(vertex1.hashCode()).isEqualTo(vertex2.hashCode());
    }
}
