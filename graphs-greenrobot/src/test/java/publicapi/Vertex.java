package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.testframework.*;
import org.greenrobot.eventbus.*;

import java.util.*;
import java.util.stream.*;

import static com.github.moaxcp.graphs.truth.Truth.*;
import static java.util.stream.Collectors.*;

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

        var expected = propertyAddedEvents(1);

        assertThat(bus)
          .withAction(()-> graph.getVertex("A").property("name1", "value1"))
          .containsExactlyElementsIn(expected);
    }

    List<VertexPropertyAdded<String>> propertyAddedEvents(int count) {
        return IntStream.rangeClosed(1, count)
          .mapToObj(i -> propertyAddedEvent("graph", "A", "name" + i, "value" + i))
          .collect(toList());
    }

    VertexPropertyAdded<String> propertyAddedEvent(String graph, String id, String name, String value) {
        return new VertexPropertyAdded.Builder<String>().graphId(graph).vertexId(id).name(name).value(value).build();
    }

    @EventSimpleGraphs
    void addProperty2(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = propertyAddedEvents(2);

        assertThat(bus)
          .withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2"))
          .containsExactlyElementsIn(expected);
    }

    @EventSimpleGraphs
    void addProperty3(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = propertyAddedEvents(3);

        assertThat(bus)
          .withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3"))
          .containsExactlyElementsIn(expected);
    }

    @EventSimpleGraphs
    void addProperty4(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = propertyAddedEvents(4);

        assertThat(bus)
          .withAction(()-> graph.getVertex("A")
            .property("name1", "value1",
              "name2", "value2",
              "name3", "value3",
              "name4", "value4"))
          .containsExactlyElementsIn(expected);
    }

    @EventSimpleGraphs
    void addProperty5(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = propertyAddedEvents(5);

        assertThat(bus)
          .withAction(()-> graph.getVertex("A")
            .property("name1", "value1",
              "name2", "value2",
              "name3", "value3",
              "name4", "value4",
              "name5", "value5"))
          .containsExactlyElementsIn(expected);
    }

    @EventSimpleGraphs
    void addProperty6(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = propertyAddedEvents(6);

        assertThat(bus)
          .withAction(()-> graph.getVertex("A")
            .property("name1", "value1",
              "name2", "value2",
              "name3", "value3",
              "name4", "value4",
              "name5", "value5",
              "name6", "value6")).containsExactlyElementsIn(expected);
    }

    @EventSimpleGraphs
    void addProperty7(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = propertyAddedEvents(7);

        assertThat(bus)
          .withAction(()-> graph.getVertex("A")
            .property("name1", "value1",
              "name2", "value2",
              "name3", "value3",
              "name4", "value4",
              "name5", "value5",
              "name6", "value6",
              "name7", "value7")).containsExactlyElementsIn(expected);
    }

    @EventSimpleGraphs
    void addProperty8(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = propertyAddedEvents(8);

        assertThat(bus)
          .withAction(()-> graph.getVertex("A")
            .property("name1", "value1",
              "name2", "value2",
              "name3", "value3",
              "name4", "value4",
              "name5", "value5",
              "name6", "value6",
              "name7", "value7",
              "name8", "value8")).containsExactlyElementsIn(expected);
    }

    @EventSimpleGraphs
    void addProperty9(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = propertyAddedEvents(9);

        assertThat(bus)
          .withAction(()-> graph.getVertex("A")
            .property("name1", "value1",
              "name2", "value2",
              "name3", "value3",
              "name4", "value4",
              "name5", "value5",
              "name6", "value6",
              "name7", "value7",
              "name8", "value8",
              "name9", "value9")).containsExactlyElementsIn(expected);
    }

    @EventSimpleGraphs
    void addProperty10(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = propertyAddedEvents(10);

        assertThat(bus)
          .withAction(()-> graph.getVertex("A")
            .property("name1", "value1",
              "name2", "value2",
              "name3", "value3",
              "name4", "value4",
              "name5", "value5",
              "name6", "value6",
              "name7", "value7",
              "name8", "value8",
              "name9", "value9",
              "name10", "value10")).containsExactlyElementsIn(expected);
    }

    @EventSimpleGraphs
    void addPropertyMap(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertex("A");

        var expected = new VertexPropertyAdded.Builder<String>().graphId("graph").vertexId("A").name("name1").value("value1").build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property(Map.of("name1", "value1"))).containsExactly(expected);
    }

    @EventSimpleGraphs
    void updateProperty1(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name1", "A");

        var expected = propertyUpdatedEvents(1);

        assertThat(bus)
          .withAction(()-> graph.getVertex("A")
            .property("name1", "value1")).containsExactlyElementsIn(expected);
    }

    List<VertexPropertyUpdated<String>> propertyUpdatedEvents(int count) {
        return IntStream.rangeClosed(1, count)
          .mapToObj(i -> propertyUpdatedEvent("graph", "A", "name", "value" + i, String.valueOf('A' + i)))
          .collect(toList());
    }

    VertexPropertyUpdated<String> propertyUpdatedEvent(String graph, String id, String name, String value, String oldValue) {
        return new VertexPropertyUpdated.Builder<String>().graphId(graph).vertexId(id).name(name).value(value).oldValue(oldValue).build();
    }

    @EventSimpleGraphs
    void updateProperty2(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name1", "A", "name2", "B");

        var expected = new VertexPropertyUpdated.Builder<String>().graphId("graph").vertexId("A").name("name").oldValue("A").value("value").build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void updateProperty3(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C");

        var expected = new VertexPropertyUpdated.Builder<String>().graphId("graph").vertexId("A").name("name").oldValue("A").value("value").build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void updateProperty4(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D");

        var expected = new VertexPropertyUpdated.Builder<String>().graphId("graph").vertexId("A").name("name").oldValue("A").value("value").build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void updateProperty5(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E");

        var expected = new VertexPropertyUpdated.Builder<String>().graphId("graph").vertexId("A").name("name").oldValue("A").value("value").build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void updateProperty6(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E", "name6", "F");

        var expected = new VertexPropertyUpdated.Builder<String>().graphId("graph").vertexId("A").name("name").oldValue("A").value("value").build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void updateProperty7(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E", "name6", "F", "name7", "G");

        var expected = new VertexPropertyUpdated.Builder<String>().graphId("graph").vertexId("A").name("name").oldValue("A").value("value").build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void updateProperty8(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E", "name6", "F", "name7", "G", "name8", "H");

        var expected = new VertexPropertyUpdated.Builder<String>().graphId("graph").vertexId("A").name("name").oldValue("A").value("value").build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void updateProperty9(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E", "name6", "F", "name7", "G", "name8", "H", "name9", "I");

        var expected = new VertexPropertyUpdated.Builder<String>().graphId("graph").vertexId("A").name("name").oldValue("A").value("value").build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void updateProperty10(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E", "name6", "F", "name7", "G", "name8", "H", "name9", "I", "name10", "J");

        var expected = new VertexPropertyUpdated.Builder<String>().graphId("graph").vertexId("A").name("name").oldValue("A").value("value").build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3", "name4", "value4", "name5", "value5", "name6", "value6", "name7", "value7", "name8", "value8", "name9", "value9", "name10", "value10")).containsExactly(expected);
    }

    @EventSimpleGraphs
    void updatePropertyMap(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property(Map.of("name1", "A", "name2", "B"));

        var expected = new VertexPropertyUpdated.Builder<String>().graphId("graph").vertexId("A").name("name").oldValue("A").value("value").build();

        assertThat(bus).withAction(()-> graph.getVertex("A").property(Map.of("name1", "value1"))).containsExactly(expected);
    }

    @EventSimpleGraphs
    void removeProperty(EventGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.getVertex("A").property("name", "value");

        var expected = new VertexPropertyRemoved.Builder<String>().graphId("graph").vertexId("A").name("name").value("value").build();

        assertThat(bus).withAction(()-> graph.getVertex("A").removeProperty("name")).containsExactly(expected);
    }
}
