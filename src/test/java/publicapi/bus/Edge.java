package publicapi.bus;

import static com.github.moaxcp.graphs.Truth.*;
import static com.github.moaxcp.graphs.events.Builders.edgeRemoved;
import com.github.moaxcp.graphs.SimpleGraph;
import com.github.moaxcp.graphs.events.*;
import testframework.EventSimpleGraphs;

public class Edge {
    @EventSimpleGraphs
    void created(SimpleGraph graph) {
        graph.id("id");
        assertThat(graph).matchesEventsIn(g -> g.edge("A", "B"))
                .containsExactly(VertexCreated.class, VertexCreated.class, EdgeCreated.class).inOrder();
    }

    @EventSimpleGraphs
    void removed(SimpleGraph graph) {
        graph.id("id");
        graph.edge("A", "B");
        assertThat(graph).matchesEventsIn(g -> g.removeEdge("A", "B")).containsExactly(EdgeRemoved.class);
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
