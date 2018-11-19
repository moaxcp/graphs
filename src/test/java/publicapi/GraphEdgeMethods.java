package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import com.github.moaxcp.graphs.SimpleGraph;
import testframework.SimpleGraphs;

public class GraphEdgeMethods {

    @SimpleGraphs
    void addNewEdge(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        assertThat(graph).hasEdge("A", "B").isSameAs(edge);
    }

    @SimpleGraphs
    void newEdgeHasNoId(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge).hasIdThat().isEmpty();
    }

    @SimpleGraphs
    void newEdgeHasToFromProperties(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge).hasPropertyThat("from").hasValue("A");
        assertThat(edge).hasPropertyThat("to").hasValue("B");
    }

    @SimpleGraphs
    void newEdgeHasToFromLocalProperties(SimpleGraph graph) {
        var edge = graph.edge("A", "B");
        assertThat(edge).thatLocal("from").isEqualTo("A");
        assertThat(edge).thatLocal("to").isEqualTo("B");
    }

    @SimpleGraphs
    void addExistingEdge(SimpleGraph graph) {
        var edge1 = graph.edge("A", "B");
        var edge2 = graph.edge("A", "B");
        assertThat(edge1).isSameAs(edge2);
    }

    @SimpleGraphs
    void removeEdge(SimpleGraph graph) {
        graph.edge("A", "B");
        graph.removeEdge("A", "B");
        assertThat(graph).hasNoEdge("A", "B");
    }

    @SimpleGraphs
    void removeEdgeThatDoesNotExist(SimpleGraph graph) {
        graph.vertex("A");
        graph.vertex("B");
        graph.removeEdge("A", "B");
        assertThat(graph).hasNoEdge("A", "B");
        assertThat(graph).hasVertices("A", "B");
    }
}
