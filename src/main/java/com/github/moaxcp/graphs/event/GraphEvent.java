package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph;

public interface GraphEvent {
    Graph getGraph();

    void setGraph(Graph graph);

    GraphEvent withGraph(Graph graph);

    void check();
}
