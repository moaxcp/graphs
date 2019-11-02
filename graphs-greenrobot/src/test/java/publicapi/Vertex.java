package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.testframework.*;
import org.greenrobot.eventbus.*;

import java.util.*;

import static com.github.moaxcp.graphs.testframework.Events.*;
import static com.github.moaxcp.graphs.truth.Truth.*;

public class Vertex {

  @EventSimpleGraphs
  void updateId(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = new VertexIdUpdated.Builder<String>().graphId("graph").oldVertexId("A").vertexId("B").build();
    assertThat(bus).withAction(() -> graph.getVertex("A").id("B")).containsExactly(expected);
  }

  @EventSimpleGraphs
  void updatePropertyAlreadyExists(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "value1");

    var expected = new VertexPropertyUpdated.Builder<String>().graphId("graph").vertexId("A").name("name1").value("value1").oldValue("value1").build();

    assertThat(bus)
      .withAction(() -> graph.getVertex("A").property("name1", "value1"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void addProperty1(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = propertyAddedEvents(1);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A").property("name1", "value1"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void addProperty2(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = propertyAddedEvents(2);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A").property("name1", "value1", "name2", "value2"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void addProperty3(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = propertyAddedEvents(3);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A").property("name1", "value1", "name2", "value2", "name3", "value3"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void addProperty4(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A");

    var expected = propertyAddedEvents(4);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
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
      .withAction(() -> graph.getVertex("A")
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
      .withAction(() -> graph.getVertex("A")
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
      .withAction(() -> graph.getVertex("A")
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
      .withAction(() -> graph.getVertex("A")
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
      .withAction(() -> graph.getVertex("A")
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
      .withAction(() -> graph.getVertex("A")
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

    assertThat(bus).withAction(() -> graph.getVertex("A").property(Map.of("name1", "value1"))).containsExactly(expected);
  }

  @EventSimpleGraphs
  void updateProperty1(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A");

    var expected = propertyUpdatedEvents(1);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1")).containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty2(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B");

    var expected = propertyUpdatedEvents(2);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2")).containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty3(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C");

    var expected = propertyUpdatedEvents(3);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3")).containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty4(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A",
      "name2", "B", "name3",
      "C", "name4", "D");

    var expected = propertyUpdatedEvents(4);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4")).containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty5(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E");

    var expected = propertyUpdatedEvents(5);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5")).containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty6(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E", "name6", "F");

    var expected = propertyUpdatedEvents(6);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6")).containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty7(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E", "name6", "F", "name7", "G");

    var expected = propertyUpdatedEvents(7);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7")).containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty8(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E", "name6", "F", "name7", "G", "name8", "H");

    var expected = propertyUpdatedEvents(8);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
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
  void updateProperty9(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E", "name6", "F", "name7", "G", "name8", "H", "name9", "I");

    var expected = propertyUpdatedEvents(9);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
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
  void updateProperty10(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C", "name4", "D", "name5", "E", "name6", "F", "name7", "G", "name8", "H", "name9", "I", "name10", "J");

    var expected = propertyUpdatedEvents(10);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
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
  void updatePropertyMap(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property(Map.of("name1", "A", "name2", "B"));

    var expected = propertyUpdatedEvents(2);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A")
        .property(Map.of("name1", "value1", "name2", "value2"))).containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void removeProperty(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name", "value");

    var expected = new VertexPropertyRemoved.Builder<String>().graphId("graph").vertexId("A").name("name").value("value").build();

    assertThat(bus).withAction(() -> graph.getVertex("A").removeProperty("name")).containsExactly(expected);
  }
}
