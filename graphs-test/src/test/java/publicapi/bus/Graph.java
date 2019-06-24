package publicapi.bus;

import com.github.moaxcp.graphs.EventGraph;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.greenrobot.DirectedEventGraph;
import com.github.moaxcp.graphs.greenrobot.UndirectedEventGraph;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import testframework.EventSimpleGraphs;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static testframework.MethodSources.testEventBus;

public class Graph {
    @Test
    void undirectedEventGraphDefaultConstructor() {
        EventBus bus = testEventBus();
        assertThat(bus).withAction(() -> new UndirectedEventGraph(bus)).containsExactly(new UndirectedGraphCreated.Builder<String>().build());
    }

    @Test
    void undirectedEventGraphId() {
        EventBus bus = testEventBus();
        assertThat(bus).withAction(() -> new UndirectedEventGraph<>("id", bus)).containsExactly(new UndirectedGraphCreated.Builder<String>().graphId("id").build());
    }

    @Test
    void directedEventGraphDefaultConstructor() {
        EventBus bus = testEventBus();
        assertThat(bus).withAction(() -> new DirectedEventGraph(bus)).containsExactly(new DirectedGraphCreated.Builder<String>().build());
    }

    @Test
    void directedEventGraphId() {
        EventBus bus = testEventBus();
        assertThat(bus).withAction(() -> new DirectedEventGraph<>("id", bus)).containsExactly(new DirectedGraphCreated.Builder<String>().graphId("id").build());
    }

    @EventSimpleGraphs
    void addProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        assertThat(graph).with(bus).hasEventsIn(g -> g.property("name", "value")).containsExactly(GraphPropertyAdded.class);
    }

    @EventSimpleGraphs
    void removeProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.property("name", "value");
        assertThat(graph).with(bus).hasEventsIn(g -> g.removeProperty("name")).containsExactly(GraphPropertyRemoved.class);
    }

    @EventSimpleGraphs
    void updateProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.property("name", "value");
        assertThat(graph).with(bus).hasEventsIn(g -> g.property("name", "value2")).containsExactly(GraphPropertyUpdated.class);
    }

    @EventSimpleGraphs
    void addId(EventGraph<String> graph, EventBus bus) {
        assertThat(graph).with(bus).hasEventsIn(g-> g.id("graph")).containsExactly(GraphIdAdded.class);
    }

    @EventSimpleGraphs
    void removeId(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        assertThat(graph).with(bus).hasEventsIn(g-> g.id(null)).containsExactly(GraphIdRemoved.class);
    }

    @EventSimpleGraphs
    void updateId(EventGraph<String> graph, EventBus bus) {
        graph.id("id1");
        assertThat(graph).with(bus).hasEventsIn(g-> g.id("id2")).containsExactly(GraphIdUpdated.class);
    }
}
