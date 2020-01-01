package com.github.moaxcp.graphs.testframework;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.greenrobot.*;
import org.greenrobot.eventbus.*;
import org.junit.jupiter.params.provider.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.util.function.Function.*;
import static org.junit.jupiter.params.provider.Arguments.*;

public class MethodSources {

    public static EventBus testEventBus() {
        return EventBus.builder()
                .throwSubscriberException(true)
                .logNoSubscriberMessages(false)
                .build();
    }

    public static Stream<Graph<String>> simpleGraphs() {
        return Stream.of(
                new UndirectedGraph<>(),
                new DirectedGraph<>(),
                new UndirectedEventGraph<>(testEventBus()),
                new DirectedEventGraph<>(testEventBus()));
    }

    public static Stream<Graph<String>> undirectedSimpleGraphs() {
        return Stream.of(
                new UndirectedGraph<>(),
                new UndirectedEventGraph<>(testEventBus()));
    }

    public static Stream<Graph<String>> directedSimpleGraphs() {
        return Stream.of(
                new DirectedGraph<>(),
                new DirectedEventGraph<>(testEventBus()));
    }

    public static Stream<Graph<String>> nonEventSimpleGraphs() {
        return Stream.of(
            new UndirectedGraph<>(),
            new DirectedGraph<>());
    }

    public static Stream<Arguments> eventSimpleGraphs() {
        EventBus undirected = testEventBus();
        EventBus directed = testEventBus();
        return Stream.of(
                Arguments.arguments(new UndirectedEventGraph<>(undirected), undirected),
                Arguments.arguments(new DirectedEventGraph<>(directed), directed));
    }

    private static Stream<Arguments> graphsPostOrder() {
        return Stream.of(
          simpleGraphArguments(MethodSources::one),
          simpleGraphArguments(MethodSources::oneLinked),
          simpleGraphArguments(MethodSources::two),
          simpleGraphArguments(MethodSources::twoLinked),
          simpleGraphArguments(MethodSources::three),
          simpleGraphArguments(MethodSources::threeLinked),
          simpleGraphArguments(MethodSources::threeSplit),
          simpleGraphArguments(MethodSources::splitJoin),
          directedGraphArguments(MethodSources::complexOneTraversal),
          directedGraphArguments(MethodSources::complexTwoTraversal),
          directedGraphArguments(MethodSources::complexTwoComponents)
        ).flatMap(identity());
    }

    private static Stream<Arguments> simpleGraphArguments(Function<Graph<String>, Arguments> convert) {
        return simpleGraphs()
          .map(convert);
    }

    private static Stream<Arguments> directedGraphArguments(Function<Graph<String>, Arguments> convert) {
        return directedSimpleGraphs()
          .map(convert);
    }

    public static Arguments complexTwoComponents(Graph<String> graph) {
        graph.edge("A", "B")
          .edge("B", "C")
          .edge("B", "D")
          .edge("D", "E")
          .edge("D", "C")
          .edge("A", "D")
          .edge("D", "A")
          .edge("A", "E")
          .edge("F", "G")
          .edge("G", "D");

        graph.edge("X", "Y")
          .edge("X", "Z")
          .edge("X", "W");

        return arguments("complex two components post order", graph, List.of("C", "E", "D", "B", "A", "G", "F", "Y", "Z","W", "X"));
    }

    private static Arguments complexTwoTraversal(Graph<String> graph) {
        graph.edge("A", "B")
          .edge("B", "C")
          .edge("B", "D")
          .edge("D", "E")
          .edge("D", "C")
          .edge("A", "D")
          .edge("D", "A")
          .edge("A", "E")
          .edge("F", "G")
          .edge("G", "D");

        return arguments("complex two traversal post order", graph, List.of("C", "E", "D", "B", "A", "G", "F"));
    }

    private static Arguments complexOneTraversal(Graph<String> graph) {
        graph.edge("A", "B")
          .edge("B", "C")
          .edge("B", "D")
          .edge("D", "E")
          .edge("D", "C")
          .edge("A", "D")
          .edge("D", "A")
          .edge("A", "E");

        return arguments("complex one traversal post order", graph, List.of("C", "E", "D", "B", "A"));
    }

    private static Arguments splitJoin(Graph<String> graph) {
        graph.edge("A", "B")
          .edge("A", "C")
          .edge("B", "D")
          .edge("C", "D");

        if(graph.isDirected()) {
            return arguments("split join directed post order", graph, List.of("D", "B", "C", "A"));
        } else {
            return arguments("split join undirected post order", graph, List.of("C", "D", "B", "A"));
        }
    }

    private static Arguments threeSplit(Graph<String> graph) {
        graph.edge("A", "B")
          .edge("A", "C");
        return arguments("three split post order", graph, List.of("B", "C", "A"));
    }

    private static Arguments threeLinked(Graph<String> graph) {
        graph.edge("A", "B")
          .edge("B", "C");
        return arguments("three linked post order", graph, List.of("C", "B", "A"));
    }

    private static Arguments twoLinked(Graph<String> graph) {
        graph.edge("A", "B");
        return arguments("two linked post order", graph, List.of("B", "A"));
    }

    private static Arguments three(Graph<String> graph) {
        graph.vertex("A")
          .vertex("B")
          .vertex("C");
        return arguments("two post order", graph, List.of("A", "B", "C"));
    }

    private static Arguments two(Graph<String> graph) {
        graph.vertex("A")
          .vertex("B");
        return arguments("two post order", graph, List.of("A", "B"));
    }

    private static Arguments oneLinked(Graph<String> graph) {
        graph.edge("A", "A");
        return arguments("one linked post order", graph, List.of("A"));
    }

    private static Arguments one(Graph<String> graph) {
        graph.vertex("A");
        return arguments("one post order", graph, List.of("A"));
    }
}
