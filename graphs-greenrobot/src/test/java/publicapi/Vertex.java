package publicapi;

import com.github.moaxcp.graphs.EventGraph;
import com.github.moaxcp.graphs.events.VertexIdUpdated;
import com.github.moaxcp.graphs.events.VertexPropertiesEvent;
import com.github.moaxcp.graphs.events.VertexPropertyRemoved;
import com.github.moaxcp.graphs.testframework.EventSimpleGraphs;
import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class Vertex {

    @EventSimpleGraphs
    void updateId(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = new VertexIdUpdated.Builder<String>().graphId("graph").oldVertexId("A").vertexId("B").build();
        assertThat(bus).withAction(() -> graph.getVertex("A").id("B")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void addProperty1(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name", "value")).build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name", "value")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void addProperty2(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2")).build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void addProperty3(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2", "name3", "value3")).build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void addProperty4(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4")).build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void addProperty5(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5")).build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void addProperty6(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6")).build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void addProperty7(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7")).build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void addProperty8(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8")).build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void addProperty9(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9")).build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void addProperty10(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10")).build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void addPropertyMap(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").newProperties(Map.of("name1", "value1")).build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property(Map.of("name1", "value1"))).containsExactly(expected);
    }

    @EventSimpleGraphs
    void updateProperty1(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name", "A");

        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").originalProperties(Map.of("name", "A")).newProperties(Map.of("name", "value")).build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name", "value")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void updateProperty2(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name1", "A", "name2", "B");

        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").originalProperties(Map.of("name1", "A", "name2", "B")).newProperties(Map.of("name1", "value1", "name2", "value2")).build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void updateProperty3(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C");

        var expected = new VertexPropertiesEvent.Builder<String>().graphId("graph").vertexId("A").originalProperties(Map.of("name1", "A", "name2", "B", "name3", "C")).newProperties(Map.of("name1", "value1", "name2", "value2", "name3", "value3")).build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void removeProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name", "value");

        var expected = new VertexPropertyRemoved.Builder<String>().graphId("graph").vertexId("A").name("name").value("value").build();

        assertThat(bus).withAction(()-> graph.getVertex("A").removeProperty("name")).containsExactly(expected);
    }
}
