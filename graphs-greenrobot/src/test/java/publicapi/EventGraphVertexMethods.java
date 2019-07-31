package publicapi;

import com.github.moaxcp.graphs.EventGraph;
import com.github.moaxcp.graphs.events.EdgeRemoved;
import com.github.moaxcp.graphs.events.VertexCreated;
import com.github.moaxcp.graphs.events.VertexPropertiesEvent;
import com.github.moaxcp.graphs.events.VertexRemoved;
import com.github.moaxcp.graphs.testframework.EventSimpleGraphs;
import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class EventGraphVertexMethods {
    @EventSimpleGraphs
    void createdWithVertex(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").build();
        assertThat(bus).withAction(() -> graph.vertex("A")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithVertex1(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithVertex2(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithVertex3(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2", "name3", "value3")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithVertex4(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithVertex5(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithVertex6(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithVertex7(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithVertex8(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithVertex9(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithVertex10(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithVertexMap(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", Map.of("name1", "value1")))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void updatedWithVertex1(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void updatedWithVertex2(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void updatedWithVertex3(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2", "name3", "value3")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void updatedWithVertex4(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void updatedWithVertex5(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void updatedWithVertex6(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void updatedWithVertex7(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void updatedWithVertex8(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void updatedWithVertex9(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void updatedWithVertex10(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void updatedWithVertexMap(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");
        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1")).build();

        assertThat(bus).withAction(() -> graph.vertex("A", Map.of("name1", "value1")))
            .containsExactly(expected);
    }
    @EventSimpleGraphs
    void createdWithGetVertex(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").build();
        assertThat(bus).withAction(() -> graph.getVertex("A")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithGetVertex1(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1")).build();

        assertThat(bus).withAction(() -> graph.getVertex("A", "name1", "value1"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithGetVertex2(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2")).build();

        assertThat(bus).withAction(() -> graph.getVertex("A", "name1", "value1", "name2", "value2"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithGetVertex3(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2", "name3", "value3")).build();

        assertThat(bus).withAction(() -> graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithGetVertex4(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4")).build();

        assertThat(bus).withAction(() -> graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithGetVertex5(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5")).build();

        assertThat(bus).withAction(() -> graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithGetVertex6(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6")).build();

        assertThat(bus).withAction(() -> graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithGetVertex7(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7")).build();

        assertThat(bus).withAction(() -> graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithGetVertex8(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8")).build();

        assertThat(bus).withAction(() -> graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithGetVertex9(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9")).build();

        assertThat(bus).withAction(() -> graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithGetVertex10(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10")).build();

        assertThat(bus).withAction(() -> graph.getVertex("A", "name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10"))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void createdWithGetVertexMap(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var expected = new VertexCreated.Builder<String>().graphId("graph").vertexId("A").properties(Map.of("name1", "value1")).build();

        assertThat(bus).withAction(() -> graph.getVertex("A", Map.of("name1", "value1")))
            .containsExactly(expected);
    }

    @EventSimpleGraphs
    void notCreated(EventGraph<String> graph, EventBus bus) {
        graph.vertex("A");
        assertThat(bus).withAction(() -> graph.vertex("A")).isEmpty();
    }

    @EventSimpleGraphs
    void remove(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = new VertexRemoved.Builder<String>().graphId("graph").vertexId("A").build();
        assertThat(bus).withAction(() -> graph.removeVertex("A")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void removeWithEdges(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.edge("A", "B");
        graph.edge("A", "C");
        graph.edge("A", "D");

        var expected1 = new EdgeRemoved.Builder<String>().graphId("graph").from("A").to("B").build();
        var expected2 = new EdgeRemoved.Builder<String>().graphId("graph").from("A").to("C").build();
        var expected3 = new EdgeRemoved.Builder<String>().graphId("graph").from("A").to("D").build();
        var expected4 = new VertexRemoved.Builder<String>().graphId("graph").vertexId("A").build();

        assertThat(bus).withAction(() -> graph.removeVertex("A"))
            .containsExactly(expected1, expected2, expected3, expected4).inOrder();
    }
}
