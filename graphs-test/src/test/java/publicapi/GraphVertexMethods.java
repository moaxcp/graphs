package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.SimpleGraphs;

import java.util.Map;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraphVertexMethods {

    @SimpleGraphs
    void vertex(Graph<String> graph) {
        Graph<String> r = graph.vertex("A");
        assertThat(r).isSameAs(graph);
        assertThat(graph).hasVertex("A");
    }

    @SimpleGraphs
    void getVertex(Graph<String> graph) {
        var vertex = graph.getVertex("id");
        assertThat(graph).hasVertex("id").isSameAs(vertex);
        assertThat(vertex).hasId("id");
    }

    @SimpleGraphs
    void getVertexExisting(Graph<String> graph) {
        var vertexA = graph.getVertex("A");
        var vertexB = graph.getVertex("A");
        assertThat(vertexA).isSameAs(vertexB);
    }

    @SimpleGraphs
    void getVertexIdIsSet(Graph<String> graph) {
        var vertex = graph.getVertex("A");
        assertThat(vertex).hasId("A");
    }

    @SimpleGraphs
    void vertexWithMapProperties(Graph<String> graph) {
        graph.vertex("A", Map.of("name1", "value1", "name2", "value2"));

        assertThat(graph).hasVertex("A").withProperty("name1").hasValue("value1");
        assertThat(graph).hasVertex("A").withProperty("name2").hasValue("value2");
    }

    @SimpleGraphs
    void getVertexWithMapProperties(Graph<String> graph) {
        var vertex = graph.getVertex("A", Map.of("name1", "value1", "name2", "value2"));

        assertThat(vertex).withProperty("name1").hasValue("value1");
        assertThat(vertex).withProperty("name2").hasValue("value2");
        assertThat(graph).hasVertex("A").withProperty("name1").hasValue("value1");
        assertThat(graph).hasVertex("A").withProperty("name2").hasValue("value2");
    }

    @SimpleGraphs
    void vertexWithMapPropertiesExisting(Graph<String> graph) {
        graph.vertex("A");
        graph.vertex("A", Map.of("name1", "value1", "name2", "value2"));

        assertThat(graph).hasVertex("A").withProperty("name1").hasValue("value1");
        assertThat(graph).hasVertex("A").withProperty("name2").hasValue("value2");
    }

    @SimpleGraphs
    void getVertexWithMapPropertiesExisting(Graph<String> graph) {
        graph.vertex("A");
        graph.getVertex("A", Map.of("name1", "value1", "name2", "value2"));

        assertThat(graph).hasVertex("A").withProperty("name1").hasValue("value1");
        assertThat(graph).hasVertex("A").withProperty("name2").hasValue("value2");
    }

    @SimpleGraphs
    void removeVertex(Graph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("Z", "Y");

        graph.removeVertex("A");

        assertThat(graph).hasNoVertex("A");
        assertThat(graph).hasNoEdge("A", "B");
        assertThat(graph).hasNoEdge("A", "C");
        assertThat(graph).hasEdge("Z", "Y");
    }

    @SimpleGraphs
    void removeVertexThatDoesNotExist(Graph<String> graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeVertex("A"));
        assertThat(thrown).hasMessageThat().isEqualTo("vertex 'A' not found.");
    }

    @SimpleGraphs
    void removeVertexWithNullId(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeVertex(null));
        assertThat(thrown).hasMessageThat().isEqualTo("id must not be null.");
    }
}
