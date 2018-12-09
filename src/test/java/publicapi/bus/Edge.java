package publicapi.bus;

import static com.github.moaxcp.graphs.Truth.assertThat;
import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import testframework.EventSimpleGraphs;

public class Edge {
    @EventSimpleGraphs
    void created(SimpleEventGraph graph) {
        graph.id("id");
        assertThat(graph).hasEventsIn(g -> g.edge("A", "B"))
            .containsExactly(VertexCreated.class, VertexCreated.class, EdgeCreated.class).inOrder();
    }

    @EventSimpleGraphs
    void notCreated(SimpleEventGraph graph) {
        graph.id("id");
        graph.edge("A", "B").id("id");
        assertThat(graph).hasNoEventsIn(g -> g.edge("A", "B"));
    }

    @EventSimpleGraphs
    void removed(SimpleEventGraph graph) {
        graph.id("id");
        graph.edge("A", "B");
        assertThat(graph).hasEventsIn(g -> g.removeEdge("A", "B")).containsExactly(EdgeRemoved.class);
    }

    @EventSimpleGraphs
    void removeWithId(SimpleEventGraph graph) {
        graph.id("graph");
        graph.edge("A", "B").id("edge");
        assertThat(graph).hasEventsIn(g -> g.removeEdge("edge")).containsExactly(EdgeRemoved.class);
    }

    @EventSimpleGraphs
    void addProperty(SimpleEventGraph graph) {
        graph.id("graph");
        graph.edge("A", "B").id("edge");
        assertThat(graph)
            .hasEventsIn(g -> g.edge("A", "B").property("name", "value"))
            .containsExactly(EdgePropertyAdded.class);
    }

    @EventSimpleGraphs
    void removeProperty(SimpleEventGraph graph) {
        graph.id("graph");
        graph.edge("A", "B").id("edge").property("name", "value");
        assertThat(graph)
            .hasEventsIn(g -> g.edge("A", "B").removeProperty("name"))
            .containsExactly(EdgePropertyRemoved.class);
    }

    @EventSimpleGraphs
    void updateProperty(SimpleEventGraph graph) {
        graph.id("graph");
        graph.edge("A", "B").id("edge").property("name", "value");
        assertThat(graph)
            .hasEventsIn(g -> g.edge("A", "B").property("name", "value2"))
            .containsExactly(EdgePropertyUpdated.class);
    }
}
