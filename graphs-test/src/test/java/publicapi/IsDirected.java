package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.DirectedSimpleGraphs;
import com.github.moaxcp.graphs.testframework.UndirectedSimpleGraphs;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class IsDirected {
    @DirectedSimpleGraphs
    void isDirected(Graph graph) {
        assertThat(graph).isDirected();
    }

    @DirectedSimpleGraphs
    void isEdgeDirected(Graph graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).isDirected();
    }

    @UndirectedSimpleGraphs
    void isUndirected(Graph graph) {
        assertThat(graph).isNotDirected();
    }

    @UndirectedSimpleGraphs
    void isEdgeUndirected(Graph graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).isNotDirected();
    }
}
