package publicapi;

import com.github.moaxcp.graphs.DirectedGraph;
import com.github.moaxcp.graphs.Graph;
import com.github.moaxcp.graphs.UndirectedGraph;
import com.github.moaxcp.graphs.greenrobot.DirectedEventGraph;
import com.github.moaxcp.graphs.greenrobot.UndirectedEventGraph;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static com.github.moaxcp.graphs.testframework.MethodSources.testEventBus;

public class GraphConstructor {

    static Stream<Graph> id() {
        return Stream.of(
                new UndirectedGraph("id"),
                new DirectedGraph("id"),
                new UndirectedEventGraph("id", testEventBus()),
                new DirectedEventGraph("id", testEventBus())
        );
    }

    @ParameterizedTest
    @MethodSource("id")
    void hasId(Graph graph) {
        assertThat(graph).hasIdThat().hasValue("id");
    }
}
