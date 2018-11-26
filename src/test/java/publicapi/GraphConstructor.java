package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import com.github.moaxcp.graphs.*;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class GraphConstructor {

    static Stream<SimpleGraph> id() {
        return Stream.of(
                new UndirectedGraph("id"),
                new DirectedGraph("id"),
                new UndirectedEventGraph("id"),
                new DirectedEventGraph("id")
        );
    }

    @ParameterizedTest
    @MethodSource("id")
    void hasId(SimpleGraph graph) {
        assertThat(graph).hasIdThat().hasValue("id");
    }
}
