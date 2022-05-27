package publicapi;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.testframework.TestDirectedGraphs;
import com.github.moaxcp.graphs.testframework.TestUndirectedGraphs;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class IsDirected {
    @TestDirectedGraphs
    void isDirected(PropertyGraph<String> graph) {
        assertThat(graph).isDirected();
    }

    @TestDirectedGraphs
    void isEdgeDirected(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).isDirected();
    }

    @TestUndirectedGraphs
    void isUndirected(PropertyGraph<String> graph) {
        assertThat(graph).isNotDirected();
    }

    @TestUndirectedGraphs
    void isEdgeUndirected(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B");
        assertThat(edge).isNotDirected();
    }
}
