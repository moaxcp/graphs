package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.testframework.*;
import org.greenrobot.eventbus.*;

import java.util.*;

import static com.github.moaxcp.graphs.testframework.Events.*;
import static com.github.moaxcp.graphs.truth.Truth.*;

public class EdgeProperties {

  @TestGreenrobotEventGraphs
  void addProperty1(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyEvent(1);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B").property("name1", "value1"))
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void addProperty2(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyEvent(2);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2"))
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void addProperty3(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyEvent(3);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3"))
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void addProperty4(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyEvent(4);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4"))
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void addProperty5(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyEvent(5);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5"))
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void addProperty6(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyEvent(6);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6"))
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void addProperty7(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyEvent(7);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7"))
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void addProperty8(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyEvent(8);

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
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void addProperty9(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyEvent(9);

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
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void addProperty10(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyEvent(10);

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
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void addPropertyMap(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge");

    var expected = edgePropertyEvent(2);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property(Map.of("name1", "value1",
          "name2", "value2")))
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void updateProperty1(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge").property("name1", "A");

    var expected = edgePropertyEvent(1);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B").property("name1", "value1"))
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void updateProperty2(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge")
      .property("name1", "A",
    "name2", "B");

    var expected = edgePropertyEvent(2);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2"))
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void updateProperty3(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge")
      .property("name1", "A",
        "name2", "B",
        "name3", "C");

    var expected = edgePropertyEvent(3);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3"))
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void updateProperty4(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D");

    var expected = edgePropertyEvent(4);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4"))
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void updateProperty5(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E");

    var expected = edgePropertyEvent(5);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5"))
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void updateProperty6(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E",
        "name6", "F");

    var expected = edgePropertyEvent(6);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6"))
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void updateProperty7(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E",
        "name6", "F",
        "name7", "G");

    var expected = edgePropertyEvent(7);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property("name1", "value1",
          "name2", "value2",
          "name3", "value3",
          "name4", "value4",
          "name5", "value5",
          "name6", "value6",
          "name7", "value7"))
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void updateProperty8(EventPropertyGraph<String> graph, EventBus bus) {
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

    var expected = edgePropertyEvent(8);

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
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void updateProperty9(EventPropertyGraph<String> graph, EventBus bus) {
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

    var expected = edgePropertyEvent(9);

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
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void updateProperty10(EventPropertyGraph<String> graph, EventBus bus) {
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

    var expected = edgePropertyEvent(10);

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
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void updatePropertyMap(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge")
      .property("name1", "A",
        "name2", "B");

    var expected = edgePropertyEvent(2);

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B")
        .property(Map.of("name1", "value1",
          "name2", "value2")))
      .containsExactly(expected);
  }

  @TestGreenrobotEventGraphs
  void updateProperty1Remove(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge").property("name1", "value1");

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B").property("name1", null))
      .containsExactly(EdgePropertyEvent.<String>builder()
        .graphId("graph")
        .edgeId("edge")
        .sourceId("A")
        .targetId("B")
        .property("name1", null)
        .build());
  }

  @TestGreenrobotEventGraphs
  void removeProperty(EventPropertyGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getEdge("A", "B").id("edge").property("name", "value");

    var expected = EdgePropertyEvent.<String>builder().graphId("graph").sourceId("A").targetId("B").edgeId("edge").property("name", null).build();

    assertThat(bus)
      .withAction(() -> graph.getEdge("A", "B").removeProperty("name"))
      .containsExactly(expected);
  }
}
