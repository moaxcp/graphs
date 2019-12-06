package publicapi.events;

import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.events.GraphEvent.Builder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PropertyEventBuilders {

    static Stream<Builder<String, ? extends Builder>> propertyEventsMissingName() {
        return Stream.of(
            new EdgePropertyAdded.Builder<>(),
            new EdgePropertyRemoved.Builder<>(),
            new EdgePropertyUpdated.Builder<>(),
            new VertexPropertyRemoved.Builder<>()
        );
    }

    static Stream<Builder<String, ? extends Builder>> propertyEventsMissingValue() {
        return Stream.of(
            new EdgePropertyAdded.Builder<String>().name("name"),
            new EdgePropertyRemoved.Builder<String>().name("name"),
            new EdgePropertyUpdated.Builder<String>().name("name"),
            new VertexPropertyRemoved.Builder<String>().name("name")
        );
    }

    @ParameterizedTest
    @MethodSource("propertyEventsMissingName")
    void eventsMissingPropertyName(Builder builder) {
        Throwable thrown = assertThrows(NullPointerException.class, builder::build);
        assertThat(thrown).hasMessageThat().isEqualTo("name must not be null.");
    }

    @ParameterizedTest
    @MethodSource("propertyEventsMissingValue")
    void eventsMissingPropertyValue(Builder builder) {
        Throwable thrown = assertThrows(NullPointerException.class, builder::build);
        assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
    }
}
