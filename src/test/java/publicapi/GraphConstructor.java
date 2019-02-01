package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static testframework.MethodSources.testEventBus;
import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.greenrobot.*;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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
