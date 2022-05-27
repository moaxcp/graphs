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

    public static Stream<PropertyGraph<String>> simpleGraphs() {
        return Stream.of(
                new UndirectedPropertyGraph<>(),
                new DirectedPropertyGraph<>(),
                new UndirectedEventPropertyGraph<>(testEventBus()),
                new DirectedEventPropertyGraph<>(testEventBus()));
    }

    public static Stream<PropertyGraph<String>> undirectedSimpleGraphs() {
        return Stream.of(
                new UndirectedPropertyGraph<>(),
                new UndirectedEventPropertyGraph<>(testEventBus()));
    }

    public static Stream<PropertyGraph<String>> directedSimpleGraphs() {
        return Stream.of(
                new DirectedPropertyGraph<>(),
                new DirectedEventPropertyGraph<>(testEventBus()));
    }

    public static Stream<PropertyGraph<String>> nonEventSimpleGraphs() {
        return Stream.of(
            new UndirectedPropertyGraph<>(),
            new DirectedPropertyGraph<>());
    }

    public static Stream<Arguments> eventSimpleGraphs() {
        EventBus undirected = testEventBus();
        EventBus directed = testEventBus();
        return Stream.of(
                Arguments.arguments(new UndirectedEventPropertyGraph<>(undirected), undirected),
                Arguments.arguments(new DirectedEventPropertyGraph<>(directed), directed));
    }

    public static Stream<Arguments> graphsPostOrder() {
        return graphsOrdered(POST_ORDER);
    }

    public static Stream<Arguments> graphsPreOrder() {
        return graphsOrdered(PRE_ORDER);
    }

    public static Stream<Arguments> graphsBreadthFirst() {
        return graphsOrdered(BREADTH_FIRST);
    }

    private static Stream<Arguments> graphsOrdered(PathOrder order) {
        return Stream.of(
          simpleGraphArguments(g -> one(g, order)),
          simpleGraphArguments(g -> oneLinked(g, order)),
          simpleGraphArguments(g -> two(g, order)),
          simpleGraphArguments(g -> twoLinked(g, order)),
          simpleGraphArguments(g -> three(g, order)),
          simpleGraphArguments(g -> threeLinked(g, order)),
          simpleGraphArguments(g -> threeSplit(g, order)),
          simpleGraphArguments(g -> splitJoin(g, order)),
          directedGraphArguments(g -> complexOneTraversal(g, order)),
          directedGraphArguments(g -> complexTwoTraversal(g, order)),
          directedGraphArguments(g -> complexTwoComponents(g, order)),
          directedGraphArguments(g -> startVertexIsBlack(g, order)),
          directedGraphArguments(g -> startHasDuplicates(g, order))
        ).flatMap(identity());
    }

    private static Stream<Arguments> simpleGraphArguments(Function<PropertyGraph<String>, Arguments> convert) {
        return simpleGraphs()
          .map(convert);
    }

    private static Stream<Arguments> directedGraphArguments(Function<PropertyGraph<String>, Arguments> convert) {
        return directedSimpleGraphs()
          .map(convert);
    }

    private static Arguments startHasDuplicates(PropertyGraph<String> graph, PathOrder order) {
        graph.edge("A", "B")
          .edge("A", "C")
          .edge("B", "D")
          .edge("X", "Z");

        var name = createName("start has duplicates", graph, order);

        switch(order) {
            case POST_ORDER:
                return arguments(name, graph, startOrder("B", "B"), List.of("D", "B", "C", "A", "Z", "X"));
            case PRE_ORDER:
                return arguments(name, graph, startOrder("B", "B"), List.of("B", "D", "A", "C", "X", "Z"));
            case BREADTH_FIRST:
                return arguments(name, graph, startOrder("B", "B"), List.of("B", "D", "A", "C", "X", "Z"));
            default:
                throw new IllegalArgumentException("not supported: " + order);

        }
    }

    private static Arguments startVertexIsBlack(PropertyGraph<String> graph, PathOrder order) {
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
        var name = createName("start vertex is black", graph, order);

        switch (order) {
            case POST_ORDER:
                return arguments(name, graph, startOrder("A", "D"), List.of("C", "E", "D", "B", "A", "G", "F"));
            case PRE_ORDER:
                return arguments(name, graph, startOrder("A", "D"), List.of("A", "B", "C", "D", "E", "F", "G"));
            case BREADTH_FIRST:
                return arguments(name, graph, startOrder("A", "D"), List.of("A", "B", "D", "E", "C", "F", "G"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    public static Arguments complexTwoComponents(PropertyGraph<String> graph, PathOrder order) {
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
        var name = createName("complex two components", graph, order);

        switch(order) {
            case POST_ORDER:
                return arguments(name, graph, startOrder(), List.of("C", "E", "D", "B", "A", "G", "F", "Y", "Z","W", "X"));
            case PRE_ORDER:
                return arguments(name, graph, startOrder(), List.of("A", "B", "C", "D", "E", "F", "G", "X", "Y", "Z", "W"));
            case BREADTH_FIRST:
                return arguments(name, graph, startOrder(), List.of("A", "B", "D", "E", "C", "F", "G", "X", "Y", "Z", "W"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    private static Arguments complexTwoTraversal(PropertyGraph<String> graph, PathOrder order) {
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
        var name = createName("complex two traversal", graph, order);

        switch(order) {
            case POST_ORDER:
                return arguments(name, graph, startOrder(), List.of("C", "E", "D", "B", "A", "G", "F"));
            case PRE_ORDER:
                return arguments(name, graph, startOrder(), List.of("A", "B", "C", "D", "E", "F", "G"));
            case BREADTH_FIRST:
                return arguments(name, graph, startOrder(), List.of("A", "B", "D", "E", "C", "F", "G"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    private static Arguments complexOneTraversal(PropertyGraph<String> graph, PathOrder order) {
        graph.edge("A", "B")
          .edge("B", "C")
          .edge("B", "D")
          .edge("D", "E")
          .edge("D", "C")
          .edge("A", "D")
          .edge("D", "A")
          .edge("A", "E");
        var name = createName("complex one traversal", graph, order);

        switch(order) {
            case POST_ORDER:
                return arguments(name, graph, startOrder(), List.of("C", "E", "D", "B", "A"));
            case PRE_ORDER:
                return arguments(name, graph, startOrder(), List.of("A", "B", "C", "D", "E"));
            case BREADTH_FIRST:
                return arguments(name, graph, startOrder(), List.of("A", "B", "D", "E", "C"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    private static Arguments splitJoin(PropertyGraph<String> graph, PathOrder order) {
        graph.edge("A", "B")
          .edge("A", "C")
          .edge("B", "D")
          .edge("C", "D");
        var name = createName("split join", graph, order);

        switch(order) {
            case POST_ORDER:
                if (graph.isDirected()) {
                    return arguments(name, graph, startOrder(), List.of("D", "B", "C", "A"));
                } else {
                    return arguments(name, graph, startOrder(), List.of("C", "D", "B", "A"));
                }
            case PRE_ORDER:
                return arguments(name, graph, startOrder(), List.of("A", "B", "D", "C"));
            case BREADTH_FIRST:
                return arguments(name, graph, startOrder(), List.of("A", "B", "C", "D"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    private static Arguments threeSplit(PropertyGraph<String> graph, PathOrder order) {
        graph.edge("A", "B")
          .edge("A", "C");
        var name = createName("three split", graph, order);

        switch(order) {
            case POST_ORDER:
                return arguments(name, graph, startOrder(), List.of("B", "C", "A"));
            case PRE_ORDER:
            case BREADTH_FIRST:
                return arguments(name, graph, startOrder(), List.of("A", "B", "C"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    private static Arguments threeLinked(PropertyGraph<String> graph, PathOrder order) {
        graph.edge("A", "B")
          .edge("B", "C");
        var name = createName("three linked", graph, order);

        switch(order) {
            case POST_ORDER:
                return arguments(name, graph, startOrder(), List.of("C", "B", "A"));
            case PRE_ORDER:
            case BREADTH_FIRST:
                return arguments(name, graph, startOrder(), List.of("A", "B", "C"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    private static Arguments three(PropertyGraph<String> graph, PathOrder order) {
        graph.vertex("A")
          .vertex("B")
          .vertex("C");
        var name = createName("two", graph, order);

        return arguments(name, graph, startOrder(), List.of("A", "B", "C"));
    }

    private static Arguments twoLinked(PropertyGraph<String> graph, PathOrder order) {
        graph.edge("A", "B");
        var name = createName("two linked", graph, order);

        switch(order) {
            case POST_ORDER:
                return arguments(name, graph, startOrder(), List.of("B", "A"));
            case PRE_ORDER:
            case BREADTH_FIRST:
                return arguments(name, graph, startOrder(), List.of("A", "B"));
            default:
                throw new IllegalArgumentException("not supported: " + order);
        }
    }

    private static Arguments two(PropertyGraph<String> graph, PathOrder order) {
        graph.vertex("A")
          .vertex("B");
        var name = createName("two", graph, order);

        return arguments(name, graph, startOrder(), List.of("A", "B"));
    }

    private static Arguments oneLinked(PropertyGraph<String> graph, PathOrder order) {
        graph.edge("A", "A");
        var name = createName("one linked", graph, order);

        return arguments(name, graph, startOrder(), List.of("A"));
    }

    private static Arguments one(PropertyGraph<String> graph, PathOrder order) {
        graph.vertex("A");
        var name = createName("one vertex", graph, order);

        return arguments(name, graph, startOrder(), List.of("A"));
    }

    private static String[] startOrder(String... start) {
        return start;
    }

    private static String createName(String prefix, PropertyGraph<String> graph, PathOrder order) {
        return prefix + " " + getDirected(graph) + " " + order;
    }

    private static String getDirected(PropertyGraph<String> graph) {
        if(graph.isDirected()) {
            return "directed graph";
        }
        return "undirected graph";
    }
}
