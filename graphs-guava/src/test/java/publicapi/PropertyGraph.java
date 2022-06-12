package publicapi;

import com.github.moaxcp.graphs.EventPropertyGraph;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.guava.DirectedEventPropertyGraph;
import com.github.moaxcp.graphs.guava.UndirectedEventPropertyGraph;
import com.github.moaxcp.graphs.testframework.TestGuavaEventGraphs;
import com.google.common.eventbus.EventBus;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static com.github.moaxcp.graphs.testframework.MethodSources.testGuavaEventBus;
import static com.github.moaxcp.graphs.truth.Truth.assertThat;

public class PropertyGraph {
    @Test
    void undirectedEventGraphDefaultConstructor() {
        EventBus bus = testGuavaEventBus();
        assertThat(bus).withAction(() -> new UndirectedEventPropertyGraph(bus)).containsExactly(UndirectedGraphCreatedEvent.<String>builder().build());
    }

    @Test
    void undirectedEventGraphId() {
        EventBus bus = testGuavaEventBus();
        assertThat(bus).withAction(() -> new UndirectedEventPropertyGraph<>("id", bus)).containsExactly(UndirectedGraphCreatedEvent.<String>builder().graphId("id").build());
    }

    @Test
    void directedEventGraphDefaultConstructor() {
        EventBus bus = testGuavaEventBus();
        assertThat(bus).withAction(() -> new DirectedEventPropertyGraph(bus)).containsExactly(DirectedGraphCreatedEvent.<String>builder().build());
    }

    @Test
    void directedEventGraphId() {
        EventBus bus = testGuavaEventBus();
        assertThat(bus).withAction(() -> new DirectedEventPropertyGraph<>("id", bus)).containsExactly(DirectedGraphCreatedEvent.<String>builder().graphId("id").build());
    }

    @TestGuavaEventGraphs
    void addId(EventPropertyGraph<String> graph, EventBus bus) {
        var event = GraphPropertyEvent.<String>builder()
          .graphId(null)
          .newId("graph")
          .build();
        assertThat(bus)
          .withAction(()-> graph.id("graph"))
          .containsExactly(event);
    }

    @TestGuavaEventGraphs
    void removeId(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");

        var event = GraphPropertyEvent.<String>builder()
          .graphId("graph")
          .newId(null)
          .build();

        assertThat(bus)
          .withAction(()-> graph.id(null))
          .containsExactly(event);
    }

    @TestGuavaEventGraphs
    void updateId(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("id1");

        var event = GraphPropertyEvent.<String>builder()
          .graphId("id1")
          .newId("id2")
          .build();

        assertThat(bus)
          .withAction(()-> graph.id("id2"))
          .containsExactly(event);
    }

    @TestGuavaEventGraphs
    void addProperty(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var event = GraphPropertyEvent.<String>builder()
          .graphId("graph")
          .property("name", "value")
          .build();
        assertThat(bus).withAction(() -> graph.property("name", "value")).containsExactly(event);
    }

    @TestGuavaEventGraphs
    void removeProperty(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.property("name", "value");
        var event = GraphPropertyEvent.<String>builder()
          .graphId("graph")
          .property("name", null)
          .build();
        assertThat(bus).withAction(() -> graph.removeProperty("name")).containsExactly(event);
    }

    @TestGuavaEventGraphs
    void updateProperty(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.property("name", "value");
        var event = GraphPropertyEvent.<String>builder()
          .graphId("graph")
          .property("name", "value2")
          .build();
        assertThat(bus).withAction(() -> graph.property("name", "value2")).containsExactly(event);
    }

    @TestGuavaEventGraphs
    void addAllEdgesProperty(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var event = EdgeInheritedPropertyEvent.<String>builder()
          .graphId("graph")
          .properties(Map.of("name", "value"))
          .build();
        assertThat(bus).withAction(() -> graph.edgeProperty("name", "value")).containsExactly(event);
    }

    @TestGuavaEventGraphs
    void removeAllEdgesProperty(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.edgeProperty("name", "value");
        var event = EdgeInheritedPropertyEvent.<String>builder()
          .graphId("graph")
          .property("name", null)
          .build();
        assertThat(bus).withAction(() -> graph.removeEdgeProperty("name")).containsExactly(event);
    }

    @TestGuavaEventGraphs
    void updateAllEdgesProperty(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.edgeProperty("name", "A");
        var event = EdgeInheritedPropertyEvent.<String>builder()
          .graphId("graph")
          .properties(Map.of("name", "value"))
          .build();
        assertThat(bus).withAction(() -> graph.edgeProperty("name", "value")).containsExactly(event);
    }

    @TestGuavaEventGraphs
    void addAllVerticesProperty(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var event = VertexInheritedPropertyEvent.<String>builder()
          .graphId("graph")
          .property("name", "value")
          .build();
        assertThat(bus).withAction(() -> graph.vertexProperty("name", "value")).containsExactly(event);
    }

    @TestGuavaEventGraphs
    void removeAllVerticesProperty(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertexProperty("name", "value");
        var event = VertexInheritedPropertyEvent.<String>builder()
          .graphId("graph")
          .property("name", null)
          .build();
        assertThat(bus).withAction(() -> graph.removeVertexProperty("name")).containsExactly(event);
    }

    @TestGuavaEventGraphs
    void updateAllVerticesProperty(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertexProperty("name", "A");
        var event = VertexInheritedPropertyEvent.<String>builder()
          .graphId("graph")
          .property("name", "value")
          .build();
        assertThat(bus).withAction(() -> graph.vertexProperty("name", "value")).containsExactly(event);
    }
}
