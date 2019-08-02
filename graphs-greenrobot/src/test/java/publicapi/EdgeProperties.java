package publicapi;

import com.github.moaxcp.graphs.EventGraph;
import com.github.moaxcp.graphs.events.EdgePropertyAdded;
import com.github.moaxcp.graphs.events.EdgePropertyRemoved;
import com.github.moaxcp.graphs.events.EdgePropertyUpdated;
import com.github.moaxcp.graphs.testframework.EventSimpleGraphs;
import org.greenrobot.eventbus.EventBus;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class EdgeProperties {

    @EventSimpleGraphs
    void addProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.edge("A", "B").id("edge");
        assertThat(graph).with(bus)
            .hasEventsIn(g -> g.getEdge("A", "B").property("name", "value")).containsExactly(EdgePropertyAdded.class);
    }

    @EventSimpleGraphs
    void removeProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("A", "B").id("edge").property("name", "value");
        assertThat(graph).with(bus)
            .hasEventsIn(g -> g.getEdge("A", "B").removeProperty("name")).containsExactly(EdgePropertyRemoved.class);
    }

    @EventSimpleGraphs
    void updateProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getEdge("A", "B").id("edge").property("name", "value");
        assertThat(graph).with(bus)
            .hasEventsIn(g -> g.getEdge("A", "B").property("name", "value2")).containsExactly(EdgePropertyUpdated.class);
    }
}
