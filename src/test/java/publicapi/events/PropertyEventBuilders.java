package publicapi.events;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.events.GraphEvent.Builder;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class PropertyEventBuilders {

    static Stream<Builder<String, ? extends Builder>> propertyEventsMissingName() {
        return Stream.of(
            new AllEdgesPropertyAdded.Builder<>(),
            new AllEdgesPropertyRemoved.Builder<>(),
            new AllEdgesPropertyUpdated.Builder<>(),
            new AllVerticesPropertyAdded.Builder<>(),
            new AllVerticesPropertyRemoved.Builder<>(),
            new AllVerticesPropertyUpdated.Builder<>(),
            new EdgePropertyAdded.Builder<>(),
            new EdgePropertyRemoved.Builder<>(),
            new EdgePropertyUpdated.Builder<>(),
            new GraphPropertyAdded.Builder<>(),
            new GraphPropertyRemoved.Builder<>(),
            new GraphPropertyUpdated.Builder<>(),
            new VertexPropertyAdded.Builder<>(),
            new VertexPropertyRemoved.Builder<>(),
            new VertexPropertyUpdated.Builder<>()
        );
    }

    static Stream<Builder<String, ? extends Builder>> propertyEventsMissingValue() {
        return Stream.of(
            new AllEdgesPropertyAdded.Builder<String>().name("name"),
            new AllEdgesPropertyRemoved.Builder<String>().name("name"),
            new AllEdgesPropertyUpdated.Builder<String>().name("name"),
            new AllVerticesPropertyAdded.Builder<String>().name("name"),
            new AllVerticesPropertyRemoved.Builder<String>().name("name"),
            new AllVerticesPropertyUpdated.Builder<String>().name("name"),
            new EdgePropertyAdded.Builder<String>().name("name"),
            new EdgePropertyRemoved.Builder<String>().name("name"),
            new EdgePropertyUpdated.Builder<String>().name("name"),
            new GraphPropertyAdded.Builder<String>().name("name"),
            new GraphPropertyRemoved.Builder<String>().name("name"),
            new GraphPropertyUpdated.Builder<String>().name("name"),
            new VertexPropertyAdded.Builder<String>().name("name"),
            new VertexPropertyRemoved.Builder<String>().name("name"),
            new VertexPropertyUpdated.Builder<String>().name("name")
        );
    }

    static Stream propertyUpdatedEventsMissingOldValue() {
        return Stream.of(
            new AllEdgesPropertyUpdated.Builder<String>().name("name").value("value"),
            new AllVerticesPropertyUpdated.Builder<String>().name("name").value("value"),
            new GraphPropertyUpdated.Builder<String>().name("name").value("value")
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

    @ParameterizedTest
    @MethodSource("propertyUpdatedEventsMissingOldValue")
    void eventsMissingPropertyOldValue(Builder builder) {
        Throwable thrown = assertThrows(NullPointerException.class, builder::build);
        assertThat(thrown).hasMessageThat().isEqualTo("oldValue must not be null.");
    }
}
