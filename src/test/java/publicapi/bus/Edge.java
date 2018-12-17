package publicapi.bus;

import static com.github.moaxcp.graphs.Truth.assertThat;
import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import testframework.EventSimpleGraphs;

public class Edge {
    @EventSimpleGraphs
    void created(SimpleEventGraph<String> graph) {
        graph.id("id");
        assertThat(graph).hasEventsIn(g -> g.edge("A", "B"))
            .containsExactly(VertexCreated.class, VertexCreated.class, EdgeCreated.class).inOrder();
    }

    @EventSimpleGraphs
    void notCreated(SimpleEventGraph<String> graph) {
        graph.id("id");
        graph.edge("A", "B").id("id");
        assertThat(graph).hasNoEventsIn(g -> g.edge("A", "B"));
    }

    @EventSimpleGraphs
    void removed(SimpleEventGraph<String> graph) {
        graph.id("id");
        graph.edge("A", "B");
        assertThat(graph).hasEventsIn(g -> g.removeEdge("A", "B"));
    }

    @EventSimpleGraphs
    void removeWithId(SimpleEventGraph<String> graph) {
        graph.id("graph");
        graph.edge("A", "B").id("edge");
        assertThat(graph).hasEventsIn(g -> g.removeEdge("edge"));
    }

    @EventSimpleGraphs
    void addProperty(SimpleEventGraph<String> graph) {
        graph.id("graph");
        graph.edge("A", "B").id("edge");
        assertThat(graph)
            .hasEventsIn(g -> g.edge("A", "B").property("name", "value"));
    }

    @EventSimpleGraphs
    void removeProperty(SimpleEventGraph<String> graph) {
        graph.id("graph");
        graph.edge("A", "B").id("edge").property("name", "value");
        assertThat(graph)
            .hasEventsIn(g -> g.edge("A", "B").removeProperty("name"));
    }

    @EventSimpleGraphs
    void updateProperty(SimpleEventGraph<String> graph) {
        graph.id("graph");
        graph.edge("A", "B").id("edge").property("name", "value");
        assertThat(graph)
            .hasEventsIn(g -> g.edge("A", "B").property("name", "value2"));
    }

    @EventSimpleGraphs
    void changeId(SimpleEventGraph<String> graph) {
        graph.edge("A", "B");
        assertThat(graph)
            .hasEventsIn(g -> g.edge("A", "B").id("edge"));
    }
}
