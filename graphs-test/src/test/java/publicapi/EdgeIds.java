package publicapi;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.testframework.SimpleGraphs;

import static com.google.common.truth.Truth.assertThat;

public class EdgeIds {

    @SimpleGraphs
    void defaultValue(Graph<String> graph) {
        assertThat(graph.getEdgeIds()).isEmpty();
    }

    @SimpleGraphs
    void canAddEdgeId(Graph<String> graph) {
        var edge = graph.getEdge("A", "B").id("id");
        assertThat(graph.getEdgeIds()).containsEntry("id", edge);
    }
}
