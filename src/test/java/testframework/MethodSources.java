package testframework;

import com.github.moaxcp.graphs.*;
import java.util.stream.Stream;

public class MethodSources {
    public static Stream<SimpleGraph> simpleGraphs() {
        return Stream.of(
                new UndirectedGraph(),
                new DirectedGraph(),
                new UndirectedEventGraph(),
                new DirectedEventGraph());
    }

    public static Stream<SimpleGraph> undirectedSimpleGraphs() {
        return Stream.of(
                new UndirectedGraph(),
                new UndirectedEventGraph());
    }

    public static Stream<SimpleGraph> directedSimpleGraphs() {
        return Stream.of(
                new DirectedGraph(),
                new DirectedEventGraph());
    }

    public static Stream<SimpleGraph> eventSimpleGraphs() {
        return Stream.of(
                new UndirectedEventGraph(),
                new DirectedEventGraph());
    }
}
