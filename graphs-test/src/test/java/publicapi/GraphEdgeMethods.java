package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.SimpleGraphs;

import java.util.Map;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraphEdgeMethods {

    @SimpleGraphs
    void edge(Graph<String> graph) {
        Graph<String> r = graph.edge("A", "B");
        assertThat(r).isSameInstanceAs(graph);
        assertThat(graph).hasEdge("A", "B");
    }

    @SimpleGraphs
    void getEdge(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(graph).hasEdge("A", "B").isSameInstanceAs(edge);
        assertThat(edge).hasToThat().isEqualTo("B");
        assertThat(edge).hasFromThat().isEqualTo("A");
    }

    @SimpleGraphs
    void getEdgeExisting(Graph<String> graph) {
        var edge1 = graph.getEdge("A", "B");
        var edge2 = graph.getEdge("A", "B");
        assertThat(edge1).isSameInstanceAs(edge2);
    }

    @SimpleGraphs
    void getEdgeHasNoId(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).hasIdThat().isEmpty();
    }

    @SimpleGraphs
    void getEdgeFromIsSet(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).hasFromThat().isEqualTo("A");
    }

    @SimpleGraphs
    void getEdgeToIsSet(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).hasToThat().isEqualTo("B");
    }

    @SimpleGraphs
    void edgeWithMapProperties(Graph<String> graph) {
        graph.edge("A", "B", Map.of("name1", "value1", "name2", "value2"));

        assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
        assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
    }

    @SimpleGraphs
    void getEdgeWithMapProperties(Graph<String> graph) {
        var edge = graph.getEdge("A", "B", Map.of("name1", "value1", "name2", "value2"));

        assertThat(edge).withProperty("name1").hasValue("value1");
        assertThat(edge).withProperty("name2").hasValue("value2");
        assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
        assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
    }

    @SimpleGraphs
    void edgeWithMapPropertiesExisting(Graph<String> graph) {
        graph.edge("A", "B");
        graph.edge("A", "B", Map.of("name1", "value1", "name2", "value2"));

        assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
        assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
    }

    @SimpleGraphs
    void getEdgeWithMapPropertiesExisting(Graph<String> graph) {
        graph.edge("A", "B");
        graph.getEdge("A", "B", Map.of("name1", "value1", "name2", "value2"));

        assertThat(graph).hasEdge("A", "B").withProperty("name1").hasValue("value1");
        assertThat(graph).hasEdge("A", "B").withProperty("name2").hasValue("value2");
    }

    @SimpleGraphs
    void removeEdge(Graph<String> graph) {
        graph.edge("A", "B");
        graph.removeEdge("A", "B");
        assertThat(graph).hasNoEdge("A", "B");
    }

    @SimpleGraphs
    void removeEdgeThatDoesNotExist(Graph<String> graph) {
        graph.vertex("A");
        graph.vertex("B");
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("A", "B"));
        assertThat(thrown).hasMessageThat().isEqualTo("edge from 'A' to 'B' not found.");
    }

    @SimpleGraphs
    void removeEdgeWithIdNullId(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null));
        assertThat(thrown).hasMessageThat().isEqualTo("id must not be null.");
    }

    @SimpleGraphs
    void removeEdgeWithId(Graph<String> graph) {
        graph.getEdge("A", "B").id("id");
        graph.removeEdge("id");
        assertThat(graph).hasNoEdge("id");
    }

    @SimpleGraphs
    void removeByFromTo(Graph<String> graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("A", "B"));
        assertThat(thrown).hasMessageThat().isEqualTo("edge from 'A' to 'B' not found.");
    }

    @SimpleGraphs
    void removeById(Graph<String> graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeEdge("id"));
        assertThat(thrown).hasMessageThat().isEqualTo("edge with id 'id' not found.");
    }

    @SimpleGraphs
    void removeWithNullId(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null));
        assertThat(thrown).hasMessageThat().isEqualTo("id must not be null.");
    }

    @SimpleGraphs
    void removeWithNullFrom(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge(null, "B"));
        assertThat(thrown).hasMessageThat().isEqualTo("from must not be null.");
    }

    @SimpleGraphs
    void removeWithNullTo(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.removeEdge("A", null));
        assertThat(thrown).hasMessageThat().isEqualTo("to must not be null.");
    }

    @SimpleGraphs
    void findEdgeMissingFrom(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () ->graph.findEdge(null, null));
        assertThat(thrown).hasMessageThat().isEqualTo("from must not be null.");
    }

    @SimpleGraphs
    void findEdgeMissingTo(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () ->graph.findEdge("A", null));
        assertThat(thrown).hasMessageThat().isEqualTo("to must not be null.");
    }

    @SimpleGraphs
    void findEdge(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(graph.findEdge("A", "B")).hasValue(edge);
    }

    @SimpleGraphs
    void findEdgeMissingId(Graph<String> graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () ->graph.findEdge(null));
        assertThat(thrown).hasMessageThat().isEqualTo("id must not be null.");
    }

    @SimpleGraphs
    void findEdgeById(Graph<String> graph) {
        var edge = graph.getEdge("A", "B").id("id");
        assertThat(graph.findEdge("id")).hasValue(edge);
    }
}
