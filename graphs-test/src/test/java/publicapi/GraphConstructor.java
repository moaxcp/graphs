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
                new UndirectedLinkedGraph("id"),
                new DirectedLinkedGraph("id"),
                new UndirectedEventLinkedGraph("id", testEventBus()),
                new DirectedEventLinkedGraph("id", testEventBus())
        );
    }

    @ParameterizedTest
    @MethodSource("id")
    void hasId(Graph graph) {
        assertThat(graph).hasIdThat().hasValue("id");
    }
}
