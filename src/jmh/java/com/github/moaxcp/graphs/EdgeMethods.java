package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.SimpleGraph.*;
import java.util.Set;
import org.openjdk.jmh.annotations.*;

@State(Scope.Thread)
public class EdgeMethods {

    @Param({"2", "100"})
    public int vertices;

    @Param({"true", "false"})
    public boolean directed;

    private SimpleGraph<Integer> graph;
    private Vertex<Integer> lastVertex;
    private Integer lastFrom;
    private Integer lastTo;

    @Setup(Level.Trial)
    public void setup() {
        if(directed) {
            graph = new DirectedGraph<>();
            for(int i = 0; i < vertices; i++) {
                for(int j = 0; j < vertices; j++) {
                    graph.edge(i, j);
                    lastVertex = graph.edge(j, i).toVertex();
                    lastFrom = j;
                    lastTo = i;
                }
            }
        } else {
            graph = new UndirectedGraph<>();
            for(int i = 0; i < vertices; i++) {
                for(int j = 0; j < vertices; j++) {
                    lastVertex = graph.edge(i, j).toVertex();
                    lastFrom = i;
                    lastTo = j;
                }
            }
        }
        System.out.println("edges: " + graph.getEdges().size());
    }

    @Benchmark
    public Edge<Integer> findFirstEdge() {
        return graph.edge(0, 0);
    }

    @Benchmark
    public Edge<Integer> findLastEdge() {
        return graph.edge(lastFrom, lastTo);
    }

    @Benchmark
    public Set<Edge<Integer>> adjaentEdges() {
        return lastVertex.adjacentEdges();
    }

    @Benchmark
    public Set<Edge<Integer>> inEdges() {
        return lastVertex.inEdges();
    }

    @Benchmark
    public Set<Edge<Integer>> outEdges() {
        return lastVertex.outEdges();
    }
}
