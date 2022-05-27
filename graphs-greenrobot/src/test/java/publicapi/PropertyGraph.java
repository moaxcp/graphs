package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.greenrobot.*;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.testframework.*;
import org.greenrobot.eventbus.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static com.github.moaxcp.graphs.testframework.MethodSources.*;
import static com.github.moaxcp.graphs.truth.GreenRobotEventBusSubject.*;

public class PropertyGraph {
    @Test
    void undirectedEventGraphDefaultConstructor() {
        EventBus bus = testEventBus();
        assertThat(bus).withAction(() -> new UndirectedEventPropertyGraph(bus)).containsExactly(UndirectedGraphCreatedEvent.<String>builder().build());
    }

    @Test
    void undirectedEventGraphId() {
        EventBus bus = testEventBus();
        assertThat(bus).withAction(() -> new UndirectedEventPropertyGraph<>("id", bus)).containsExactly(UndirectedGraphCreatedEvent.<String>builder().graphId("id").build());
    }

    @Test
    void directedEventGraphDefaultConstructor() {
        EventBus bus = testEventBus();
        assertThat(bus).withAction(() -> new DirectedEventPropertyGraph(bus)).containsExactly(DirectedGraphCreatedEvent.<String>builder().build());
    }

    @Test
    void directedEventGraphId() {
        EventBus bus = testEventBus();
        assertThat(bus).withAction(() -> new DirectedEventPropertyGraph<>("id", bus)).containsExactly(DirectedGraphCreatedEvent.<String>builder().graphId("id").build());
    }

    @TestEventGraphs
    void addId(EventPropertyGraph<String> graph, EventBus bus) {
        var event = GraphPropertyEvent.<String>builder()
          .graphId(null)
          .newId("graph")
          .build();
        assertThat(bus)
          .withAction(()-> graph.id("graph"))
          .containsExactly(event);
    }

    @TestEventGraphs
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

    @TestEventGraphs
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

    @TestEventGraphs
    void addProperty(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var event = GraphPropertyEvent.<String>builder()
          .graphId("graph")
          .property("name", "value")
          .build();
        assertThat(bus).withAction(() -> graph.property("name", "value")).containsExactly(event);
    }

    @TestEventGraphs
    void removeProperty(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.property("name", "value");
        var event = GraphPropertyEvent.<String>builder()
          .graphId("graph")
          .property("name", null)
          .build();
        assertThat(bus).withAction(() -> graph.removeProperty("name")).containsExactly(event);
    }

    @TestEventGraphs
    void updateProperty(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.property("name", "value");
        var event = GraphPropertyEvent.<String>builder()
          .graphId("graph")
          .property("name", "value2")
          .build();
        assertThat(bus).withAction(() -> graph.property("name", "value2")).containsExactly(event);
    }

    @TestEventGraphs
    void addAllEdgesProperty(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var event = EdgeInheritedPropertyEvent.<String>builder()
          .graphId("graph")
          .properties(Map.of("name", "value"))
          .build();
        assertThat(bus).withAction(() -> graph.edgeProperty("name", "value")).containsExactly(event);
    }

    @TestEventGraphs
    void removeAllEdgesProperty(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.edgeProperty("name", "value");
        var event = EdgeInheritedPropertyEvent.<String>builder()
          .graphId("graph")
          .property("name", null)
          .build();
        assertThat(bus).withAction(() -> graph.removeEdgeProperty("name")).containsExactly(event);
    }

    @TestEventGraphs
    void updateAllEdgesProperty(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.edgeProperty("name", "A");
        var event = EdgeInheritedPropertyEvent.<String>builder()
          .graphId("graph")
          .properties(Map.of("name", "value"))
          .build();
        assertThat(bus).withAction(() -> graph.edgeProperty("name", "value")).containsExactly(event);
    }

    @TestEventGraphs
    void addAllVerticesProperty(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        var event = VertexInheritedPropertyEvent.<String>builder()
          .graphId("graph")
          .property("name", "value")
          .build();
        assertThat(bus).withAction(() -> graph.vertexProperty("name", "value")).containsExactly(event);
    }

    @TestEventGraphs
    void removeAllVerticesProperty(EventPropertyGraph<String> graph, EventBus bus) {
        graph.id("graph");
        graph.vertexProperty("name", "value");
        var event = VertexInheritedPropertyEvent.<String>builder()
          .graphId("graph")
          .property("name", null)
          .build();
        assertThat(bus).withAction(() -> graph.removeVertexProperty("name")).containsExactly(event);
    }

    @TestEventGraphs
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
