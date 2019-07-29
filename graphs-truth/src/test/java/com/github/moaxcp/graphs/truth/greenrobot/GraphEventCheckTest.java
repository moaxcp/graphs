package com.github.moaxcp.graphs.truth.greenrobot;

import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.UndirectedGraph;
import com.github.moaxcp.graphs.events.VertexPropertiesEvent;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

public class GraphEventCheckTest {
    @Test
    void vertexPropertiesEventAdded() {
        Graph<String> graph = new UndirectedGraph<>("undirected");
        graph.vertex("A", "name1", "value1", "name2", "value2");

        VertexPropertiesEvent<String> event = new VertexPropertiesEvent.Builder<String>()
            .graphId("undirected")
            .vertexId("A")
            .newProperties(Map.of("name1", "value1", "name2", "value2"))
            .build();

        GraphEventCheck check = new GraphEventCheck(graph);
        check.vertexPropertiesEvent(event);
        assertThat(check.getEventClasses()).containsExactly(VertexPropertiesEvent.class);
    }

    @Test
    void vertexPropertiesEventUpdated() {
        Graph<String> graph = new UndirectedGraph<>("undirected");
        graph.vertex("A", "name1", "value1", "name2", "value2");

        VertexPropertiesEvent<String> event = new VertexPropertiesEvent.Builder<String>()
            .graphId("undirected")
            .vertexId("A")
            .originalProperties(Map.of("name1", "A", "name2", "B"))
            .newProperties(Map.of("name1", "value1", "name2", "value2"))
            .build();

        GraphEventCheck check = new GraphEventCheck(graph);
        check.vertexPropertiesEvent(event);
        assertThat(check.getEventClasses()).containsExactly(VertexPropertiesEvent.class);
    }
}
