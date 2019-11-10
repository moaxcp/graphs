package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.testframework.*;
import org.greenrobot.eventbus.*;

import java.util.*;

import static com.github.moaxcp.graphs.testframework.Events.*;
import static com.github.moaxcp.graphs.truth.Truth.*;

public class EdgeProperties {

  @EventSimpleGraphs
  void addProperty1(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyAddedEvents(1);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B").property("name1", "value1"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void addProperty2(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyAddedEvents(2);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void addProperty3(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyAddedEvents(3);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void addProperty4(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyAddedEvents(4);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void addProperty5(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyAddedEvents(5);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
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
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyAddedEvents(6);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void addProperty7(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyAddedEvents(7);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void addProperty8(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyAddedEvents(8);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7",
          "name8", "value8"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void addProperty9(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyAddedEvents(9);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7",
          "name8", "value8",
          "name9", "value9"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void addProperty10(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyAddedEvents(10);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7",
          "name8", "value8",
          "name9", "value9",
          "name10", "value10"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void addPropertyMap(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyAddedEvents(2);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property(Map.of("name1", "value1",
          "name2", "value2")))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty1(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge").property("name1", "A");

    var expected = edgePropertyUpdatedEvents(1);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B").property("name1", "value1"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty2(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge")
      .property("name1", "A",
    "name2", "B");

    var expected = edgePropertyUpdatedEvents(2);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty3(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge")
      .property("name1", "A",
        "name2", "B",
        "name3", "C");

    var expected = edgePropertyUpdatedEvents(3);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty4(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D");

    var expected = edgePropertyUpdatedEvents(4);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty5(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E");

    var expected = edgePropertyUpdatedEvents(5);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty6(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E",
        "name6", "F");

    var expected = edgePropertyUpdatedEvents(6);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty7(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E",
        "name6", "F",
        "name7", "G");

    var expected = edgePropertyUpdatedEvents(7);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty8(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E",
        "name6", "F",
        "name7", "G",
        "name8", "H");

    var expected = edgePropertyUpdatedEvents(8);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7",
          "name8", "value8"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty9(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E",
        "name6", "F",
        "name7", "G",
        "name8", "H",
        "name9", "I");

    var expected = edgePropertyUpdatedEvents(9);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7",
          "name8", "value8",
          "name9", "value9"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty10(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E",
        "name6", "F",
        "name7", "G",
        "name8", "H",
        "name9", "I",
        "name10", "J");

    var expected = edgePropertyUpdatedEvents(10);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7",
          "name8", "value8",
          "name9", "value9",
          "name10", "value10"))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updatePropertyMap(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge")
      .property("name1", "A",
        "name2", "B");

    var expected = edgePropertyUpdatedEvents(2);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property(Map.of("name1", "value1",
          "name2", "value2")))
      .containsExactlyElementsIn(expected);
  }

  @EventSimpleGraphs
  void updateProperty1Remove(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge").property("name1", "value1");

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B").property("name1", null))
      .containsExactly(new EdgePropertyRemoved.Builder<String>()
        .graphId("graph")
        .edgeId("edge")
        .from("A")
        .to("B")
        .name("name1")
        .value("value1")
        .build());
  }

  @EventSimpleGraphs
  void removeProperty(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge").property("name", "value");

    var expected = new EdgePropertyRemoved.Builder<String>().graphId("graph").from("A").to("B").edgeId("edge").name("name").value("value").build();

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B").removeProperty("name"))
      .containsExactly(expected);
  }
}
