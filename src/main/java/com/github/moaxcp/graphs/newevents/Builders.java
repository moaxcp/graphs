package com.github.moaxcp.graphs.newevents;

public class Builders {
    public static GraphCreated.Builder graphCreated() {
        return new GraphCreated.Builder();
    }
    public static DirectedGraphCreated.Builder directedGraphCreated() {
        return new DirectedGraphCreated.Builder();
    }
}
