package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import com.github.moaxcp.graphs.*;
import testframework.SimpleGraphs;

public class GraphVertexMethods {

    @SimpleGraphs
    void testRemoveVertex(SimpleGraph graph) {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("Z", "Y");

        graph.removeVertex("A");
        assertThat(graph.getEdges()).hasSize(1);
        assertThat(graph.getVertices()).doesNotContainKey("A");
    }

    @SimpleGraphs
    void addNewVertex(SimpleGraph graph) {
        var vertex = graph.vertex("id");
        GraphSubject.assertThat(graph).hasVertexThat("id").isSameAs(vertex);
        assertThat(vertex).hasId("id");
        assertThat(vertex).withProperty("id").hasValue("id");
        assertThat(vertex).withLocalProperty("id").isEqualTo("id");
    }

    @SimpleGraphs
    void addExistingVertex(SimpleGraph graph) {
        var vertexA = graph.vertex("A");
        var vertexB = graph.vertex("A");
        assertThat(vertexA).isSameAs(vertexB);
    }
}
