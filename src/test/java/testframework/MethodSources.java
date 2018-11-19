package testframework;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.greenrobot.*;
import java.util.stream.Stream;

public class MethodSources {
    public static Stream<SimpleGraph> simpleGraphs() {
        return Stream.of(
                new UndirectedGraph(),
                new DirectedGraph());
    }

    public static Stream<SimpleGraph> unidrectedSimpleGraphs() {
        return Stream.of(
                new UndirectedGraph(),
                new UndirectedEventGraph());
    }

    public static Stream<SimpleGraph> directedEventSimpleGraphs() {
        return Stream.of(
                new DirectedEventGraph());
    }
}
