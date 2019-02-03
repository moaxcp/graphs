package publicapi.bus;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static testframework.MethodSources.testEventBus;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.greenrobot.*;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import testframework.EventSimpleGraphs;

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
    void addProperty(EventGraph graph) {
        graph.id("graph");
        assertThat(graph).hasEventsIn(g -> g.property("name", "value")).containsExactly(GraphPropertyAdded.class);
    }

    @EventSimpleGraphs
    void removeProperty(EventGraph graph) {
        graph.id("graph");
        graph.property("name", "value");
        assertThat(graph).hasEventsIn(g -> g.removeProperty("name")).containsExactly(GraphPropertyRemoved.class);
    }

    @EventSimpleGraphs
    void updateProperty(EventGraph graph) {
        graph.id("graph");
        graph.property("name", "value");
        assertThat(graph).hasEventsIn(g -> g.property("name", "value2")).containsExactly(GraphPropertyUpdated.class);
    }

    @EventSimpleGraphs
    void addId(EventGraph<String> graph) {
        assertThat(graph).hasEventsIn(g-> g.id("graph")).containsExactly(GraphIdAdded.class);
    }

    @EventSimpleGraphs
    void removeId(EventGraph<String> graph) {
        graph.id("graph");
        assertThat(graph).hasEventsIn(g-> g.id(null)).containsExactly(GraphIdRemoved.class);
    }

    @EventSimpleGraphs
    void updateId(EventGraph<String> graph) {
        graph.id("id1");
        assertThat(graph).hasEventsIn(g-> g.id("id2")).containsExactly(GraphIdUpdated.class);
    }
}
