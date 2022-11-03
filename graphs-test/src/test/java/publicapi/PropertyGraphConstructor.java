package publicapi;

import com.github.moaxcp.graphs.DirectedPropertyGraph;
import com.github.moaxcp.graphs.ObservableUndirectedPropertyGraph;
import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.UndirectedPropertyGraph;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.github.moaxcp.graphs.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PropertyGraphConstructor {

    static Stream<PropertyGraph<String>> id() {
        return Stream.of(
                new UndirectedPropertyGraph<>("id"),
                new ObservableUndirectedPropertyGraph<>("id"),
                new DirectedPropertyGraph<>("id")
        );
    }

    static Stream<PropertyGraph<String>> noId() {
        return Stream.of(
            new UndirectedPropertyGraph<>(),
            new ObservableUndirectedPropertyGraph<>(),
            new DirectedPropertyGraph<>()
        );
    }

    @Test
    void nullId() {
        var exception = assertThrows(NullPointerException.class, () -> new UndirectedPropertyGraph<>(null));
        assertThat(exception).hasMessageThat().isEqualTo("value must not be null.");
    }

    @ParameterizedTest
    @MethodSource("id")
    void hasId(PropertyGraph<String> graph) {
        assertThat(graph).hasIdThat().hasValue("id");
    }

    @ParameterizedTest
    @MethodSource("noId")
    void doesNotHaveId(PropertyGraph<String> graph) {
        assertThat(graph).hasIdThat().isEmpty();
    }

}
