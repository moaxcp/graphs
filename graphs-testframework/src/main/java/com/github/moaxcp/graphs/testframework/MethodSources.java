package com.github.moaxcp.graphs.testframework;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.greenrobot.*;
import org.greenrobot.eventbus.*;
import org.junit.jupiter.params.provider.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static com.github.moaxcp.graphs.testframework.PathOrder.*;
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

    public static Stream<Arguments> graphsPostOrder() {
        return Stream.of(
          simpleGraphArguments(g-> one(g, POST_ORDER)),
          simpleGraphArguments(g-> oneLinked(g, POST_ORDER)),
          simpleGraphArguments(g-> two(g, POST_ORDER)),
          simpleGraphArguments(g-> twoLinked(g, POST_ORDER)),
          simpleGraphArguments(g-> three(g, POST_ORDER)),
          simpleGraphArguments(g-> threeLinked(g, POST_ORDER)),
          simpleGraphArguments(g-> threeSplit(g, POST_ORDER)),
          simpleGraphArguments(g-> splitJoin(g, POST_ORDER)),
          directedGraphArguments(g-> complexOneTraversal(g, POST_ORDER)),
          directedGraphArguments(g-> complexTwoTraversal(g, POST_ORDER)),
          directedGraphArguments(g -> complexTwoComponents(g, POST_ORDER))
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

    public static Arguments complexTwoComponents(Graph<String> graph, PathOrder order) {
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

        switch(order) {
            case POST_ORDER:
                return arguments("complex two components post order", graph, List.of("C", "E", "D", "B", "A", "G", "F", "Y", "Z","W", "X"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    private static Arguments complexTwoTraversal(Graph<String> graph, PathOrder order) {
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

        switch(order) {
            case POST_ORDER:
                return arguments("complex two traversal post order", graph, List.of("C", "E", "D", "B", "A", "G", "F"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    private static Arguments complexOneTraversal(Graph<String> graph, PathOrder order) {
        graph.edge("A", "B")
          .edge("B", "C")
          .edge("B", "D")
          .edge("D", "E")
          .edge("D", "C")
          .edge("A", "D")
          .edge("D", "A")
          .edge("A", "E");

        switch(order) {
            case POST_ORDER:
                return arguments("complex one traversal post order", graph, List.of("C", "E", "D", "B", "A"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    private static Arguments splitJoin(Graph<String> graph, PathOrder order) {
        graph.edge("A", "B")
          .edge("A", "C")
          .edge("B", "D")
          .edge("C", "D");

        switch(order) {
            case POST_ORDER:
                if (graph.isDirected()) {
                    return arguments("split join directed post order", graph, List.of("D", "B", "C", "A"));
                } else {
                    return arguments("split join undirected post order", graph, List.of("C", "D", "B", "A"));
                }
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    private static Arguments threeSplit(Graph<String> graph, PathOrder order) {
        graph.edge("A", "B")
          .edge("A", "C");
        switch(order) {
            case POST_ORDER:
                return arguments("three split post order", graph, List.of("B", "C", "A"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    private static Arguments threeLinked(Graph<String> graph, PathOrder order) {
        graph.edge("A", "B")
          .edge("B", "C");

        switch(order) {
            case POST_ORDER:
                return arguments("three linked post order", graph, List.of("C", "B", "A"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    private static Arguments three(Graph<String> graph, PathOrder order) {
        graph.vertex("A")
          .vertex("B")
          .vertex("C");

        switch(order) {
            case POST_ORDER:
                return arguments("two post order", graph, List.of("A", "B", "C"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    private static Arguments twoLinked(Graph<String> graph, PathOrder order) {
        graph.edge("A", "B");

        switch(order) {
            case POST_ORDER:
                return arguments("two linked post order", graph, List.of("B", "A"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    private static Arguments two(Graph<String> graph, PathOrder order) {
        graph.vertex("A")
          .vertex("B");

        switch(order) {
            case POST_ORDER:
                return arguments("two post order", graph, List.of("A", "B"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    private static Arguments oneLinked(Graph<String> graph, PathOrder order) {
        graph.edge("A", "A");

        switch(order) {
            case POST_ORDER:
                return arguments("one linked post order", graph, List.of("A"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    private static Arguments one(Graph<String> graph, PathOrder order) {
        graph.vertex("A");

        switch(order) {
            case POST_ORDER:
                return arguments("one post order", graph, List.of("A"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }
}
