package com.github.moaxcp.graphs.event;

import com.github.moaxcp.graphs.Graph.Vertex;

public interface VertexGraphEvent extends GraphEvent {
    Vertex getVertex();
    void setVertex(Vertex vertex);
    VertexGraphEvent withVertex(Vertex vertex);
}
