package com.github.moaxcp.graphs.testframework;

import com.github.moaxcp.graphs.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

import static com.github.moaxcp.graphs.testframework.PathOrder.*;
import static java.util.function.Function.identity;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MethodSources {

    public static Stream<PropertyGraph<String>> propertyGraphs() {
        return Stream.of(
                new UndirectedPropertyGraph<>(),
                new ObservableUndirectedPropertyGraph<>(),
                new DirectedPropertyGraph<>());
    }

    public static Stream<PropertyGraph<String>> undirectedPropertyGraphs() {
        return Stream.of(
                new UndirectedPropertyGraph<>(),
                new ObservableUndirectedPropertyGraph<>());
    }

    public static Stream<PropertyGraph<String>> directedPropertyGraphs() {
        return Stream.of(
                new DirectedPropertyGraph<>(),
                new ObservableDirectedPropertyGraph<>());
    }

    public static Stream<PropertyGraph<String>> nonEventPropertyGraphs() {
        return Stream.of(
            new UndirectedPropertyGraph<>(),
            new DirectedPropertyGraph<>());
    }

    public static Stream<ObservablePropertyGraph<String>> observablePropertyGraphs() {
        return Stream.of(
            new ObservableUndirectedPropertyGraph<>(),
            new ObservableDirectedPropertyGraph<>());
    }

    public static Stream<Arguments> graphsPostOrder() {
        return graphsOrdered(POST_ORDER);
    }

    public static Stream<Arguments> graphsReversePostOrder() {
        return graphsOrdered(REVERSE_POST_ORDER);
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
          directedGraphArguments(g -> complexTwoComponentsThreeStart(g, order)),
          directedGraphArguments(g -> startVertexIsBlack(g, order)),
          directedGraphArguments(g -> startHasDuplicates(g, order))
        ).flatMap(identity());
    }

    private static Stream<Arguments> simpleGraphArguments(Function<PropertyGraph<String>, Arguments> convert) {
        return propertyGraphs()
          .map(convert);
    }

    private static Stream<Arguments> directedGraphArguments(Function<PropertyGraph<String>, Arguments> convert) {
        return directedPropertyGraphs()
          .map(convert);
    }

    private static Arguments startHasDuplicates(PropertyGraph<String> graph, PathOrder order) {
        graph.edge("A", "B")
          .edge("A", "C")
          .edge("B", "D")
          .edge("X", "Z");

        var name = createName("start has duplicates", graph, order);

        return switch (order) {
            case POST_ORDER -> arguments(name, graph, startOrder("B", "B"), List.of("D", "B", "C", "A", "Z", "X"));
            case REVERSE_POST_ORDER -> arguments(name, graph, startOrder("B", "B"), List.of("X", "Z", "A", "C", "B", "D"));
            case PRE_ORDER, BREADTH_FIRST -> arguments(name, graph, startOrder("B", "B"), List.of("B", "D", "A", "C", "X", "Z"));
        };
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

        return switch (order) {
            case POST_ORDER -> arguments(name, graph, startOrder("A", "D"), List.of("C", "E", "D", "B", "A", "G", "F"));
            case REVERSE_POST_ORDER ->
                arguments(name, graph, startOrder("A", "D"), List.of("F", "G", "A", "B", "D", "E", "C"));
            case PRE_ORDER -> arguments(name, graph, startOrder("A", "D"), List.of("A", "B", "C", "D", "E", "F", "G"));
            case BREADTH_FIRST ->
                arguments(name, graph, startOrder("A", "D"), List.of("A", "B", "D", "E", "C", "F", "G"));
        };
    }

    public static Arguments complexTwoComponents(PropertyGraph<String> graph, PathOrder order) {
        complexTwoComponentsGraph(graph);
        String name = createName("complex two components", graph, order);

        return switch (order) {
            case POST_ORDER ->
                arguments(name, graph, startOrder(), List.of("C", "E", "D", "B", "A", "G", "F", "Y", "Z", "W", "X"));
            case REVERSE_POST_ORDER ->
                arguments(name, graph, startOrder(), List.of("X", "W", "Z", "Y", "F", "G", "A", "B", "D", "E", "C"));
            case PRE_ORDER ->
                arguments(name, graph, startOrder(), List.of("A", "B", "C", "D", "E", "F", "G", "X", "Y", "Z", "W"));
            case BREADTH_FIRST ->
                arguments(name, graph, startOrder(), List.of("A", "B", "D", "E", "C", "F", "G", "X", "Y", "Z", "W"));
        };
    }

    public static Arguments complexTwoComponentsThreeStart(PropertyGraph<String> graph, PathOrder order) {
        complexTwoComponentsGraph(graph);
        String name = createName("complex two components", graph, order);

        return switch (order) {
            case POST_ORDER ->
                arguments(name, graph, startOrder("D", "G", "W"), List.of("E", "C", "B", "A", "D", "G", "W", "F", "Y", "Z", "X"));
            case REVERSE_POST_ORDER ->
                arguments(name, graph, startOrder("D", "G", "W"), List.of("X", "Z", "Y", "F", "W", "G", "D", "A", "B", "C", "E"));
            case PRE_ORDER, BREADTH_FIRST ->
                arguments(name, graph, startOrder("D", "G", "W"), List.of("D", "E", "C", "A", "B", "G", "W", "F", "X", "Y", "Z"));
        };
    }

    private static void complexTwoComponentsGraph(PropertyGraph<String> graph) {
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

        return switch (order) {
            case POST_ORDER -> arguments(name, graph, startOrder(), List.of("C", "E", "D", "B", "A", "G", "F"));
            case REVERSE_POST_ORDER -> arguments(name, graph, startOrder(), List.of("F", "G", "A", "B", "D", "E", "C"));
            case PRE_ORDER -> arguments(name, graph, startOrder(), List.of("A", "B", "C", "D", "E", "F", "G"));
            case BREADTH_FIRST -> arguments(name, graph, startOrder(), List.of("A", "B", "D", "E", "C", "F", "G"));
        };
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

        return switch (order) {
            case POST_ORDER -> arguments(name, graph, startOrder(), List.of("C", "E", "D", "B", "A"));
            case REVERSE_POST_ORDER, BREADTH_FIRST -> arguments(name, graph, startOrder(), List.of("A", "B", "D", "E", "C"));
            case PRE_ORDER -> arguments(name, graph, startOrder(), List.of("A", "B", "C", "D", "E"));
        };
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
            case REVERSE_POST_ORDER:
                if (graph.isDirected()) {
                    return arguments(name, graph, startOrder(), List.of("A", "C", "B", "D"));
                } else {
                    return arguments(name, graph, startOrder(), List.of("A", "B", "D", "C"));
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

        return switch (order) {
            case POST_ORDER -> arguments(name, graph, startOrder(), List.of("B", "C", "A"));
            case REVERSE_POST_ORDER -> arguments(name, graph, startOrder(), List.of("A", "C", "B"));
            case PRE_ORDER, BREADTH_FIRST -> arguments(name, graph, startOrder(), List.of("A", "B", "C"));
        };
    }

    private static Arguments threeLinked(PropertyGraph<String> graph, PathOrder order) {
        graph.edge("A", "B")
          .edge("B", "C");
        var name = createName("three linked", graph, order);

        return switch(order) {
            case POST_ORDER -> arguments(name, graph, startOrder(), List.of("C", "B", "A"));
            case REVERSE_POST_ORDER, PRE_ORDER, BREADTH_FIRST -> arguments(name, graph, startOrder(), List.of("A", "B", "C"));
        };
    }

    private static Arguments three(PropertyGraph<String> graph, PathOrder order) {
        graph.vertex("A")
          .vertex("B")
          .vertex("C");
        var name = createName("three", graph, order);

        return switch(order) {
            case REVERSE_POST_ORDER -> arguments(name, graph, startOrder(), List.of("C", "B", "A"));
            case POST_ORDER, PRE_ORDER, BREADTH_FIRST -> arguments(name, graph, startOrder(), List.of("A", "B", "C"));
        };
    }

    private static Arguments twoLinked(PropertyGraph<String> graph, PathOrder order) {
        graph.edge("A", "B");
        var name = createName("two linked", graph, order);

        return switch(order) {
            case POST_ORDER -> arguments(name, graph, startOrder(), List.of("B", "A"));
            case REVERSE_POST_ORDER, PRE_ORDER, BREADTH_FIRST -> arguments(name, graph, startOrder(), List.of("A", "B"));
        };
    }

    private static Arguments two(PropertyGraph<String> graph, PathOrder order) {
        graph.vertex("A")
          .vertex("B");
        var name = createName("two", graph, order);

        return switch(order) {
            case REVERSE_POST_ORDER -> arguments(name, graph, startOrder(), List.of("B", "A"));
            case POST_ORDER, PRE_ORDER, BREADTH_FIRST -> arguments(name, graph, startOrder(), List.of("A", "B"));
        };
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
