package publicapi.bus;

import static com.github.moaxcp.graphs.Truth.assertEvents;
import static com.github.moaxcp.graphs.events.Builders.*;
import com.github.moaxcp.graphs.SimpleGraph;
import testframework.EventSimpleGraphs;

public class Edge {
    @EventSimpleGraphs
    void created(SimpleGraph graph) {
        graph.id("id");
        assertEvents(() -> graph.edge("A", "B")).containsExactly(vertexCreated().graphId("id").vertexId("A").build(),
                vertexCreated().graphId("id").vertexId("B").build(),
                edgeCreated().graphId("id").from("A").to("B").build())
        .inOrder();
    }

    @EventSimpleGraphs
    void removed(SimpleGraph graph) {
        graph.id("id");
        graph.edge("A", "B");
        assertEvents(() -> graph.removeEdge("A", "B")).containsExactly(edgeRemoved().graphId("id").from("A").to("B").build());
    }

    @EventSimpleGraphs
    void removedNoEdge(SimpleGraph graph) {
        assertEvents(() -> graph.removeEdge("A", "B")).isEmpty();
    }

    @EventSimpleGraphs
    void removeWithId(SimpleGraph graph) {
        graph.id("graph");
        graph.edge("A", "B").id("edge");
        assertEvents(() -> graph.removeEdge("edge")).containsExactly(edgeRemoved().graphId("graph").edgeId("edge").from("A").to("B").build());
    }

    @EventSimpleGraphs
    void removeWithIdNoEdge(SimpleGraph graph) {
        assertEvents(() -> graph.removeEdge("edge")).isEmpty();
    }
}
