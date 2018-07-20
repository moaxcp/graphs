package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph.Edge;

public interface EdgeGraphEvent extends GraphEvent {
    Edge getEdge();
    void setEdge(Edge edge);
    EdgeGraphEvent withEdge(Edge edge);
}
