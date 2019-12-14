package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.newevents.*;
import com.github.moaxcp.graphs.testframework.*;
import org.greenrobot.eventbus.*;

import java.util.*;

import static com.github.moaxcp.graphs.testframework.Events.*;
import static com.github.moaxcp.graphs.truth.Truth.*;

public class EventGraphVertexMethods {
  @EventSimpleGraphs
  void createdWithVertex(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(0);
    assertThat(bus)
      .withAction(() -> graph.vertex("A"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithVertex1(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(1);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithVertex2(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(2);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithVertex3(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(3);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithVertex4(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(4);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
      "name1", "value1",
      "name2", "value2",
      "name3", "value3",
      "name4", "value4"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithVertex5(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(5);

    assertThat(bus).withAction(() -> graph.vertex("A",
      "name1", "value1",
      "name2", "value2",
      "name3", "value3",
      "name4", "value4",
      "name5", "value5"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithVertex6(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(6);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithVertex7(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(7);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithVertex8(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(8);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7",
        "name8", "value8"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithVertex9(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(9);

    assertThat(bus).withAction(() -> graph.vertex("A",
      "name1", "value1",
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

  @EventSimpleGraphs
  void createdWithVertex10(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(10);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
      "name1", "value1",
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

  @EventSimpleGraphs
  void createdWithVertexMap(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(1);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        Map.of("name1", "value1")))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void updatedWithVertex1(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A");
    var expected = vertexPropertyEvents(1);

    assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void updatedWithVertex1Remove(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A", "name1", "value1");

    assertThat(bus)
      .withAction(() -> graph.vertex("A", "name1", null))
      .containsExactly(new VertexPropertyRemoved.Builder<String>()
        .graphId("graph")
        .vertexId("A")
        .name("name1")
        .value("value1")
        .build());
  }

  @EventSimpleGraphs
  void updatedWithVertex2(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B");
    var expected = vertexPropertyEvents(2);

    assertThat(bus).withAction(() -> graph.vertex("A", "name1", "value1", "name2", "value2"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void updatedWithVertex3(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A").property("name1", "A", "name2", "B", "name3", "C");
    var expected = vertexPropertyEvents(3);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void updatedWithVertex4(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D");
    var expected = vertexPropertyEvents(4);

    assertThat(bus).withAction(() -> graph.vertex("A",
      "name1", "value1",
      "name2", "value2",
      "name3", "value3",
      "name4", "value4"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void updatedWithVertex5(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
      "name5", "E");
    var expected = vertexPropertyEvents(5);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void updatedWithVertex6(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E",
        "name6", "F");
    var expected = vertexPropertyEvents(6);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void updatedWithVertex7(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E",
        "name6", "F",
      "name7", "G");
    var expected = vertexPropertyEvents(7);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void updatedWithVertex8(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E",
        "name6", "F",
        "name7", "G",
        "name8", "H");
    var expected = vertexPropertyEvents(8);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7",
        "name8", "value8"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void updatedWithVertex9(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A")
      .property("name1", "A",
        "name2", "B",
        "name3", "C",
        "name4", "D",
        "name5", "E",
        "name6", "F",
        "name7", "G",
        "name8", "H",
        "name9", "I");
    var expected = vertexPropertyEvents(9);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
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

  @EventSimpleGraphs
  void updatedWithVertex10(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A")
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
    var expected = vertexPropertyEvents(10);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        "name1", "value1",
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

  @EventSimpleGraphs
  void updatedWithVertexMap(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A")
      .property("name1", "A",
        "name2", "B");
    var expected = vertexPropertyEvents(2);

    assertThat(bus)
      .withAction(() -> graph.vertex("A",
        Map.of("name1", "value1", "name2", "value2")))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithGetVertex(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(0);
    assertThat(bus)
      .withAction(() -> graph.getVertex("A"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithGetVertex1(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(1);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A", "name1", "value1"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithGetVertex2(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(2);

    assertThat(bus)
      .withAction(() -> graph.getVertex(
        "A", "name1", "value1",
        "name2", "value2"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithGetVertex3(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(3);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithGetVertex4(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(4);

    assertThat(bus).withAction(() -> graph.getVertex("A",
      "name1", "value1",
      "name2", "value2",
      "name3", "value3",
      "name4", "value4"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithGetVertex5(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(5);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A",
      "name1", "value1",
      "name2", "value2",
      "name3", "value3",
      "name4", "value4",
      "name5", "value5"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithGetVertex6(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(6);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A",
      "name1", "value1",
      "name2", "value2",
      "name3", "value3",
      "name4", "value4",
      "name5", "value5",
      "name6", "value6"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithGetVertex7(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(7);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A",
        "name1", "value1",
        "name2", "value2",
        "name3", "value3",
        "name4", "value4",
        "name5", "value5",
        "name6", "value6",
        "name7", "value7"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithGetVertex8(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(8);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A",
      "name1", "value1",
      "name2", "value2",
      "name3", "value3",
      "name4", "value4",
      "name5", "value5",
      "name6", "value6",
      "name7", "value7",
      "name8", "value8"))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void createdWithGetVertex9(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(9);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A",
      "name1", "value1",
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

  @EventSimpleGraphs
  void createdWithGetVertex10(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(10);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A",
      "name1", "value1",
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

  @EventSimpleGraphs
  void createdWithGetVertexMap(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    var expected = vertexCreatedEvents(1);

    assertThat(bus)
      .withAction(() -> graph.getVertex("A",
        Map.of("name1", "value1")))
      .containsExactly(expected);
  }

  @EventSimpleGraphs
  void updatedWithGetVertex1Remove(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.getVertex("A", "name1", "value1");

    assertThat(bus)
      .withAction(() -> graph.getVertex("A", "name1", null))
      .containsExactly(new VertexPropertyRemoved.Builder<String>()
        .graphId("graph")
        .vertexId("A")
        .name("name1")
        .value("value1")
        .build());
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

    var expected = VertexRemovedEvent.<String>builder()
      .graphId("graph")
      .vertexId("A")
      .build();

    assertThat(bus).withAction(() -> graph.removeVertex("A")).containsExactly(expected);
  }

  @EventSimpleGraphs
  void removeWithProperties(EventGraph<String> graph, EventBus bus) {
    graph.id("graph");
    graph.vertex("A", "name1", "value1");

    var expected = VertexRemovedEvent.<String>builder()
      .graphId("graph")
      .vertexId("A")
      .build();

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
    var expected4 = VertexRemovedEvent.<String>builder()
      .graphId("graph")
      .vertexId("A")
      .build();

    assertThat(bus).withAction(() -> graph.removeVertex("A"))
      .containsExactly(expected1, expected2, expected3, expected4).inOrder();
  }
}
