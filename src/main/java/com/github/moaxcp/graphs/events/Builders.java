package com.github.moaxcp.graphs.events;

public class Builders {
    private Builders() {

    }

    public static AllEdgesPropertyAdded.Builder allEdgesPropertyAdded() {
        return AllEdgesPropertyAdded.builder();
    }

    public static AllEdgesPropertyRemoved.Builder allEdgesPropertyRemoved() {
        return AllEdgesPropertyRemoved.builder();
    }

    public static AllEdgesPropertyUpdated.Builder allEdgesPropertyUpdated() {
        return AllEdgesPropertyUpdated.builder();
    }

    public static AllVerticesPropertyAdded.Builder allVerticesPropertyAdded() {
        return AllVerticesPropertyAdded.builder();
    }

    public static AllVerticesPropertyRemoved.Builder allVerticesPropertyRemoved() {
        return AllVerticesPropertyRemoved.builder();
    }

    public static AllVerticesPropertyUpdated.Builder allVerticesPropertyUpdated() {
        return AllVerticesPropertyUpdated.builder();
    }

    public static DirectedGraphCreated.Builder directedGraphCreated() {
        return DirectedGraphCreated.builder();
    }

    public static EdgeCreated.Builder edgeCreated() {
        return EdgeCreated.builder();
    }

    public static EdgePropertyAdded.Builder edgePropertyAdded() {
        return EdgePropertyAdded.builder();
    }

    public static EdgePropertyRemoved.Builder edgePropertyRemoved() {
        return EdgePropertyRemoved.builder();
    }

    public static EdgePropertyUpdated.Builder edgePropertyUpdated() {
        return EdgePropertyUpdated.builder();
    }

    public static EdgeRemoved.Builder edgeRemoved() {
        return EdgeRemoved.builder();
    }

    public static GraphPropertyAdded.Builder graphPropertyAdded() {
        return GraphPropertyAdded.builder();
    }

    public static GraphPropertyRemoved.Builder graphPropertyRemoved() {
        return GraphPropertyRemoved.builder();
    }

    public static GraphPropertyUpdated.Builder graphPropertyUpdated() {
        return GraphPropertyUpdated.builder();
    }

    public static UndirectedGraphCreated.Builder undirectedGraphCreated() {
        return UndirectedGraphCreated.builder();
    }

    public static VertexCreated.Builder vertexCreated() {
        return VertexCreated.builder();
    }

    public static VertexPropertyAdded.Builder vertexPropertyAdded() {
        return VertexPropertyAdded.builder();
    }

    public static VertexPropertyRemoved.Builder vertexPropertyRemoved() {
        return VertexPropertyRemoved.builder();
    }

    public static VertexPropertyUpdated.Builder vertexProeprtyUpdated() {
        return VertexPropertyUpdated.builder();
    }

    public static VertexRemoved.Builder vertexRemoved() {
        return VertexRemoved.builder();
    }
}
