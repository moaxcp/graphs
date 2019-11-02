package publicapi;

import com.github.moaxcp.graphs.EventGraph;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.greenrobot.DirectedEventGraph;
import com.github.moaxcp.graphs.greenrobot.UndirectedEventGraph;
import com.github.moaxcp.graphs.testframework.EventSimpleGraphs;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;

import static com.github.moaxcp.graphs.testframework.MethodSources.testEventBus;
import static com.github.moaxcp.graphs.truth.EventBusSubject.assertThat;
import static com.github.moaxcp.graphs.truth.greenrobot.EventGraphSubject.assertThat;

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
    void addAllEdgesProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var event = new AllEdgesPropertyAdded.Builder<String>().graphId("graph").name("name").value("value").build();
        assertThat(bus).withAction(() -> graph.edgeProperty("name", "value")).containsExactly(event);
    }

    @EventSimpleGraphs
    void removeAllEdgesProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.edgeProperty("name", "value");
        var event = new AllEdgesPropertyRemoved.Builder<String>().graphId("graph").name("name").value("value").build();
        assertThat(bus).withAction(() -> graph.removeEdgeProperty("name")).containsExactly(event);
    }

    @EventSimpleGraphs
    void updateAllEdgesProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.edgeProperty("name", "A");
        var event = new AllEdgesPropertyUpdated.Builder<String>().graphId("graph").name("name").oldValue("A").value("value").build();
        assertThat(bus).withAction(() -> graph.edgeProperty("name", "value")).containsExactly(event);
    }

    @EventSimpleGraphs
    void addAllVerticesProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var event = new AllVerticesPropertyAdded.Builder<String>().graphId("graph").name("name").value("value").build();
        assertThat(bus).withAction(() -> graph.vertexProperty("name", "value")).containsExactly(event);
    }

    @EventSimpleGraphs
    void removeAllVerticesProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertexProperty("name", "value");
        var event = new AllVerticesPropertyRemoved.Builder<String>().graphId("graph").name("name").value("value").build();
        assertThat(bus).withAction(() -> graph.removeVertexProperty("name")).containsExactly(event);
    }

    @EventSimpleGraphs
    void updateAllVerticesProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertexProperty("name", "A");
        var event = new AllVerticesPropertyUpdated.Builder<String>().graphId("graph").name("name").oldValue("A").value("value").build();
        assertThat(bus).withAction(() -> graph.vertexProperty("name", "value")).containsExactly(event);
    }
}
