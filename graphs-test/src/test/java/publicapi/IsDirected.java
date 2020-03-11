package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.TestDirectedGraphs;
import com.github.moaxcp.graphs.testframework.TestUndirectedGraphs;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class IsDirected {
    @TestDirectedGraphs
    void isDirected(Graph<String> graph) {
        assertThat(graph).isDirected();
    }

    @TestDirectedGraphs
    void isEdgeDirected(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).isDirected();
    }

    @TestUndirectedGraphs
    void isUndirected(Graph<String> graph) {
        assertThat(graph).isNotDirected();
    }

    @TestUndirectedGraphs
    void isEdgeUndirected(Graph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).isNotDirected();
    }
}
