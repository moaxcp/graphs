package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.greenrobot.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.stream.*;

import static com.github.moaxcp.graphs.testframework.MethodSources.*;
import static com.github.moaxcp.graphs.truth.Truth.*;

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
    void hasId(Graph<String> graph) {
        assertThat(graph).hasIdThat().hasValue("id");
    }
}
