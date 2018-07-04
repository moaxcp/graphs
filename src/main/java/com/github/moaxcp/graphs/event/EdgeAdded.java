package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

public class EdgeAdded<K> {
    private Graph<K> graph;
    private Graph<K>.Edge edge;
    public EdgeAdded(Graph<K> graph, Graph<K>.Edge edge) {
        this.graph = graph;
        this.edge = edge;
    }
}
