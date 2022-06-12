package publicapi;

import com.github.moaxcp.graphs.*;
import com.github.moaxcp.graphs.greenrobot.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.stream.*;

import static com.github.moaxcp.graphs.testframework.MethodSources.*;
import static com.github.moaxcp.graphs.truth.Truth.*;

public class PropertyGraphConstructor {

    static Stream<PropertyGraph> id() {
        return Stream.of(
                new UndirectedPropertyGraph("id"),
                new DirectedPropertyGraph("id"),
                new UndirectedEventPropertyGraph("id", testGreenrobotEventBus()),
                new DirectedEventPropertyGraph("id", testGreenrobotEventBus())
        );
    }

    @ParameterizedTest
    @MethodSource("id")
    void hasId(PropertyGraph<String> graph) {
        assertThat(graph).hasIdThat().hasValue("id");
    }
}
