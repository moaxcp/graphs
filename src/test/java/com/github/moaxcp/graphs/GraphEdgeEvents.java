package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.event.EdgeAdded;
import com.github.moaxcp.graphs.event.EdgeEvent;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class GraphEdgeEvents {
    class EdgeEventHandler {
        EdgeEvent event;
        void handle(EdgeEvent event) {
            this.event = event;
        }
    }

    Graph graph = new Graph("graph");
    EdgeEventHandler handler = new EdgeEventHandler();

    @BeforeEach
    void setup() {
        graph.subscribe(EdgeEvent.class, handler::handle);
    }

    @Test
    void testAddNewEdgeEvent() {
        graph.edge("from", "to");
        assertThat(handler.event.getEdge()).containsExactly("from", "from", "to", "to");
    }

    @Test
    void testRemoveEdgeEvent() {
        graph.edge("from", "to");
        graph.removeEdge("from", "to");
        assertThat(handler.event.getEdge()).containsExactly("from", "from", "to", "to");
    }
}
