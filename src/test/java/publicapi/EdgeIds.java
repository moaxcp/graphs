package publicapi;

import static com.google.common.truth.Truth.assertThat;
import com.github.moaxcp.graphs.Graph;
import testframework.SimpleGraphs;

public class EdgeIds {

    @SimpleGraphs
    void defaultValue(Graph graph) {
        assertThat(graph.getEdgeIds()).isEmpty();
    }

    @SimpleGraphs
    void canAddEdgeId(Graph<String> graph) {
        var edge = graph.edge("A", "B").id("id");
        assertThat(graph.getEdgeIds()).containsEntry("id", edge);
    }
}
