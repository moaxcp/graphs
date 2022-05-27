package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.PropertyGraph.Edge;
import com.github.moaxcp.graphs.PropertyGraph.Vertex;
import org.openjdk.jmh.annotations.*;

import java.util.Set;

@State(Scope.Thread)
public class EdgeMethods {

    @Param({"2", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100"})
    public int vertices;

    @Param({"true", "false"})
    public boolean directed;

    private PropertyGraph<Integer> graph;
    private Vertex<Integer> vertex;
    private Integer lastFrom;
    private Integer lastTo;

    @Setup(Level.Trial)
    public void setup() {
        if(directed) {
            graph = new DirectedPropertyGraph<>();
            for(int i = 0; i < vertices; i++) {
                for(int j = 0; j < vertices; j++) {
                    graph.edge(i, j);
                    vertex = graph.getEdge(j, i).toVertex();
                    lastFrom = j;
                    lastTo = i;
                }
            }
        } else {
            graph = new UndirectedPropertyGraph<>();
            for(int i = 0; i < vertices; i++) {
                for(int j = 0; j < vertices; j++) {
                    vertex = graph.getEdge(i, j).toVertex();
                    lastFrom = i;
                    lastTo = j;
                }
            }
        }
        System.out.println("edges: " + graph.getEdges().size());
    }

    @Benchmark
    public Edge<Integer> findFirstEdge() {
        return graph.getEdge(0, 0);
    }

    @Benchmark
    public Edge<Integer> findLastEdge() {
        return graph.getEdge(lastFrom, lastTo);
    }

    @Benchmark
    public Set<Edge<Integer>> adjacentEdges() {
        return vertex.adjacentEdges();
    }

    @Benchmark
    public Set<Edge<Integer>> inEdges() {
        return vertex.inEdges();
    }

    @Benchmark
    public Set<Edge<Integer>> outEdges() {
        return vertex.outEdges();
    }
}
