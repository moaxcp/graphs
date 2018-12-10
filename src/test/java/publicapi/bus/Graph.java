package publicapi.bus;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static com.github.moaxcp.graphs.events.Builders.*;
import static testframework.MethodSources.testEventBus;
import com.github.moaxcp.graphs.*;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import testframework.EventSimpleGraphs;

public class Graph {
    @Test
    void undirectedEventGraphDefaultConstructor() {
        EventBus bus = testEventBus();
        assertThat(bus).withAction(() -> new UndirectedEventGraph(bus)).containsExactly(undirectedGraphCreated().build());
    }

    @Test
    void undirectedEventGraphId() {
        EventBus bus = testEventBus();
        assertThat(bus).withAction(() -> new UndirectedEventGraph("id", bus)).containsExactly(undirectedGraphCreated().graphId("id").build());
    }

    @Test
    void directedEventGraphDefaultConstructor() {
        EventBus bus = testEventBus();
        assertThat(bus).withAction(() -> new DirectedEventGraph(bus)).containsExactly(directedGraphCreated().build());
    }

    @Test
    void directedEventGraphId() {
        EventBus bus = testEventBus();
        assertThat(bus).withAction(() -> new DirectedEventGraph("id", bus)).containsExactly(directedGraphCreated().graphId("id").build());
    }

    @EventSimpleGraphs
    void addProperty(SimpleEventGraph graph) {
        graph.id("graph");
        assertThat(graph).hasEventsIn(g -> g.property("name", "value"));
    }

    @EventSimpleGraphs
    void removeProperty(SimpleEventGraph graph) {
        graph.id("graph");
        graph.property("name", "value");
        assertThat(graph).hasEventsIn(g -> g.removeProperty("name"));
    }

    @EventSimpleGraphs
    void updateProperty(SimpleEventGraph graph) {
        graph.id("graph");
        graph.property("name", "value");
        assertThat(graph).hasEventsIn(g -> g.property("name", "value2"));
    }
}
