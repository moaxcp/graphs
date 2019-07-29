package publicapi;

import com.github.moaxcp.graphs.EventGraph;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.testframework.EventSimpleGraphs;
import org.greenrobot.eventbus.EventBus;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class Vertex {
    @EventSimpleGraphs
    void created(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        assertThat(graph).with(bus).hasEventsIn(g -> g.vertex("A")).containsExactly(VertexCreated.class);
    }

    @EventSimpleGraphs
    void notCreated(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).with(bus).hasNoEventsIn(g -> g.vertex("A"));
    }

    @EventSimpleGraphs
    void remove(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).with(bus).hasEventsIn(g -> g.removeVertex("A")).containsExactly(VertexRemoved.class);
    }

    @EventSimpleGraphs
    void removeWithEdges(EventGraph<String> graph, EventBus bus) {
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("A", "D");
        assertThat(graph).with(bus).hasEventsIn(g->g.removeVertex("A"))
            .containsExactly(EdgeRemoved.class, EdgeRemoved.class, EdgeRemoved.class, VertexRemoved.class).inOrder();
    }

    @EventSimpleGraphs
    void updateId(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).with(bus).hasEventsIn(g -> g.getVertex("A").id("B")).containsExactly(VertexIdUpdated.class);
    }

    @EventSimpleGraphs
    void addProperty1(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).with(bus).hasEventsIn(g-> g.getVertex("A").property("name", "value")).containsExactly(VertexPropertiesEvent.class);
    }

    @EventSimpleGraphs
    void addProperty2(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).with(bus).hasEventsIn(g-> g.getVertex("A").property("name1", "value1", "name2", "value2")).containsExactly(VertexPropertiesEvent.class);
    }

    @EventSimpleGraphs
    void addProperty3(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).with(bus).hasEventsIn(g-> g.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3")).containsExactly(VertexPropertiesEvent.class);
    }

    @EventSimpleGraphs
    void addProperty4(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).with(bus).hasEventsIn(g-> g.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4")).containsExactly(VertexPropertiesEvent.class);
    }

    @EventSimpleGraphs
    void addProperty5(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).with(bus).hasEventsIn(g-> g.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5")).containsExactly(VertexPropertiesEvent.class);
    }

    @EventSimpleGraphs
    void addProperty6(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).with(bus).hasEventsIn(g-> g.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6")).containsExactly(VertexPropertiesEvent.class);
    }

    @EventSimpleGraphs
    void addProperty7(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).with(bus).hasEventsIn(g-> g.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7")).containsExactly(VertexPropertiesEvent.class);
    }

    @EventSimpleGraphs
    void addProperty8(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).with(bus).hasEventsIn(g-> g.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8")).containsExactly(VertexPropertiesEvent.class);
    }

    @EventSimpleGraphs
    void addProperty9(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).with(bus).hasEventsIn(g-> g.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9")).containsExactly(VertexPropertiesEvent.class);
    }

    @EventSimpleGraphs
    void addProperty10(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        assertThat(graph).with(bus).hasEventsIn(g-> g.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10")).containsExactly(VertexPropertiesEvent.class);
    }

    @EventSimpleGraphs
    void updateProperty1(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name", "A");
        assertThat(graph).with(bus).hasEventsIn(g-> g.getVertex("A").property("name", "value1")).containsExactly(VertexPropertiesEvent.class);
    }

    @EventSimpleGraphs
    void updateProperty2(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name1", "A", "name2", "B");
        assertThat(graph).with(bus).hasEventsIn(g-> g.getVertex("A").property("name1", "value1", "name2", "value2")).containsExactly(VertexPropertiesEvent.class);
    }

    @EventSimpleGraphs
    void updateProperty3(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C");
        assertThat(graph).with(bus).hasEventsIn(g-> g.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3")).containsExactly(VertexPropertiesEvent.class);
    }

    @EventSimpleGraphs
    void removeProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name", "value");
        assertThat(graph).with(bus).hasEventsIn(g-> g.getVertex("A").removeProperty("name")).containsExactly(VertexPropertyRemoved.class);
    }
}
