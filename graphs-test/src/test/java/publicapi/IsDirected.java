package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.DirectedSimpleGraphs;
import com.github.moaxcp.graphs.testframework.UndirectedSimpleGraphs;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class IsDirected {
    @DirectedSimpleGraphs
    void isDirected(Graph<String> graph) {
        assertThat(graph).isDirected();
    }

    @DirectedSimpleGraphs
    void isEdgeDirected(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).isDirected();
    }

    @UndirectedSimpleGraphs
    void isUndirected(Graph<String> graph) {
        assertThat(graph).isNotDirected();
    }

    @UndirectedSimpleGraphs
    void isEdgeUndirected(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).isNotDirected();
    }
}
