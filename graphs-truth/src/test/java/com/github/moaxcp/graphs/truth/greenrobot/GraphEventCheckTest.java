package com.github.moaxcp.graphs.truth.greenrobot;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.events.*;
import org.junit.jupiter.api.*;

import static com.google.common.truth.Truth.*;

public class GraphEventCheckTest {
    @Test
    void graphPropertyAdded() {
        Graph<String> graph = new UndirectedGraph<>("undirected");
        graph.property("name1", "value1");

        GraphPropertyAdded<String> event = new GraphPropertyAdded.Builder<String>().graphId("undirected").name("name1").value("value1").build();

        GraphEventCheck check = new GraphEventCheck(graph);
        check.graphPropertyAdded(event);
        assertThat(check.getEventClasses()).containsExactly(GraphPropertyAdded.class);
    }
}
