package publicapitests.edge;

import com.github.moaxcp.graphs.Graph;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.github.moaxcp.graphs.GraphSubject.assertThat;
import static com.github.moaxcp.graphs.EdgeSubject.assertThat;

public class Add {
    Graph graph = new Graph("graph");

    @Test
    void addNewEdge() {
        Graph.Edge edge = graph.edge("from", "to");
        assertThat(graph).hasEdge("from", "to").isSameAs(edge);
        assertThat(edge).thatProperty("from").hasValue("from");
        assertThat(edge).thatProperty("to").hasValue("to");
        assertThat(edge).id().isEmpty();
    }
}
