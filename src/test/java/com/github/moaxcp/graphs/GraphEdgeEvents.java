package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.event.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class GraphEdgeEvents {
    class EdgeEventHandler {
        List<EdgeAdded> added = new ArrayList<>();
        List<EdgeRemoved> removed = new ArrayList<>();
        List<EdgeAttributeAdded> attributeAdded = new ArrayList<>();
        List<EdgeAttributeRemoved> attributeRemoved = new ArrayList<>();
        List<EdgeAttributeUpdated> attributeUpdated = new ArrayList<>();
        void handle(EdgeEvent event) {
            if(event instanceof EdgeAdded) {
                added.add((EdgeAdded) event);
            } else if(event instanceof EdgeRemoved) {
                removed.add((EdgeRemoved) event);
            } else if(event instanceof EdgeAttributeAdded) {
                attributeAdded.add((EdgeAttributeAdded) event);
            } else if(event instanceof EdgeAttributeRemoved) {
                attributeRemoved.add((EdgeAttributeRemoved) event);
            } else if(event instanceof EdgeAttributeUpdated) {
                attributeUpdated.add((EdgeAttributeUpdated) event);
            }
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
        assertThat(handler.added).hasSize(1);
        var event = handler.added.get(0);
        assertThat(event.getEdge()).containsExactly("from", "from", "to", "to");
    }

    @Test
    void testRemoveEdgeEvent() {
        graph.edge("from", "to");
        graph.removeEdge("from", "to");
        assertThat(handler.removed).hasSize(1);
        var event = handler.removed.get(0);
        assertThat(event.getEdge()).containsExactly("from", "from", "to", "to");
    }

    @Test
    void testAddEdgeAttribute() {
        Graph.Edge edge = graph.edge("from", "to");
        edge.put("key", "value");
        assertThat(handler.attributeAdded).hasSize(1);
        var event = handler.attributeAdded.get(0);
        assertThat(event.getAttributeKey()).isEqualTo("key");
        assertThat(event.getAttributeValue()).isEqualTo("value");
    }
}
