package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.SimpleGraph.Edge;
import com.google.common.collect.*;
import java.util.*;
import org.openjdk.jmh.annotations.*;

public class EdgeMethods {

    public static final int VERTICES = 200;

    @State(Scope.Benchmark)
    public static class UndirectedState {
        SimpleGraph<Integer> graph;

        @Setup(Level.Trial)
        public void setup() {
            graph = new UndirectedGraph<>();
            ContiguousSet<Integer> integers = ContiguousSet.closedOpen(0, VERTICES);
            Set<Set<Integer>> combinations = Sets.combinations(integers, 2);
            for(Set<Integer> combination : combinations) {
                Iterator<Integer> iterator = combination.iterator();
                graph.edge(iterator.next(), iterator.next());
            }
            System.out.println("undirected graph");
            System.out.println("    vertices: " + graph.getVertices().size());
            System.out.println("    edges: " + graph.getEdges().size());
            assert graph.getVertices().size() == VERTICES;
            assert graph.findEdge(VERTICES - 2, VERTICES - 1).isPresent();
        }
    }

    @State(Scope.Benchmark)
    public static class DirectedState {
        SimpleGraph<Integer> graph;

        @Setup(Level.Trial)
        public void setup() {
            graph = new DirectedGraph<>();
            ContiguousSet<Integer> integers = ContiguousSet.closedOpen(0, VERTICES);
            Set<Set<Integer>> combinations = Sets.combinations(integers, 2);
            for(Set<Integer> combination : combinations) {
                Iterator<Integer> iterator = combination.iterator();
                Integer first = iterator.next();
                Integer second = iterator.next();
                graph.edge(first, second);
                graph.edge(second, first);
            }
            System.out.println("directed graph");
            System.out.println("    vertices: " + graph.getVertices().size());
            System.out.println("    edges: " + graph.getEdges().size());
            assert graph.getVertices().size() == VERTICES;
            assert graph.findEdge(VERTICES - 2, VERTICES - 1).isPresent();
        }
    }

    @Benchmark
    public Edge<Integer> undirectedFindLastEdge(UndirectedState undirectedState) {
        return undirectedState.graph.edge(VERTICES - 2, VERTICES - 1);
    }

    @Benchmark
    public Edge<Integer> undirectedFindFirstEdge(UndirectedState undirectedState) {
        return undirectedState.graph.edge(0, 0);
    }

    @Benchmark
    public Set<Edge<Integer>> undirectedAdjaentEdges(UndirectedState undirectedState) {
        return undirectedState.graph.vertex(0).adjacentEdges();
    }

    @Benchmark
    public Set<Edge<Integer>> undirectedInEdges(UndirectedState undirectedState) {
        return undirectedState.graph.vertex(0).inEdges();
    }

    @Benchmark
    public Set<Edge<Integer>> undirectedOutEdges(UndirectedState undirectedState) {
        return undirectedState.graph.vertex(0).outEdges();
    }

    @Benchmark
    public Edge<Integer> directedFindLastEdge(DirectedState directedState) {
        return directedState.graph.edge(VERTICES - 2, VERTICES - 1);
    }

    @Benchmark
    public Edge<Integer> directedFindFirstEdge(DirectedState directedState) {
        return directedState.graph.edge(0, 0);
    }

    @Benchmark
    public Set<Edge<Integer>> directedAdjaentEdges(DirectedState directedState) {
        return directedState.graph.vertex(0).adjacentEdges();
    }

    @Benchmark
    public Set<Edge<Integer>> directedInEdges(DirectedState directedState) {
        return directedState.graph.vertex(0).inEdges();
    }

    @Benchmark
    public Set<Edge<Integer>> directedOutEdges(DirectedState directedState) {
        return directedState.graph.vertex(0).outEdges();
    }
}
