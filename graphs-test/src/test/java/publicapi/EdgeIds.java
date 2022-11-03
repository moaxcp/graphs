package publicapi;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.testframework.TestPropertyGraphs;
import org.junit.jupiter.api.Disabled;

import static com.google.common.truth.Truth.assertThat;

public class EdgeIds {

    @TestPropertyGraphs
    void defaultValue(PropertyGraph<String> graph) {
        assertThat(graph.getEdgeIds()).isEmpty();
    }

    @TestPropertyGraphs
    void canAddEdgeId(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B").id("id");
        assertThat(graph.getEdgeIds()).containsEntry("id", edge);
    }

    @TestPropertyGraphs
    @Disabled("todo allow ids to be set as properties")
    void canAddEdgeIdAsProperty(PropertyGraph<String> graph) {
        var edge = graph.getEdge("A", "B", "id", "id");
        assertThat(graph.getEdgeIds()).containsEntry("id", edge);
    }
}
