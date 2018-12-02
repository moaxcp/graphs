package com.github.moaxcp.graphs.events;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class BuildersTest {

    static Stream<PropertyEvent.Builder> propertyEventsMissingName() {
        return Stream.of(
                new AllEdgesPropertyAdded.Builder(),
                new AllEdgesPropertyRemoved.Builder(),
                new AllEdgesPropertyUpdated.Builder(),
                new AllVerticesPropertyAdded.Builder(),
                new AllVerticesPropertyRemoved.Builder(),
                new AllVerticesPropertyUpdated.Builder(),
                new EdgePropertyAdded.Builder(),
                new EdgePropertyRemoved.Builder(),
                new EdgePropertyUpdated.Builder(),
                new GraphPropertyAdded.Builder(),
                new GraphPropertyRemoved.Builder(),
                new GraphPropertyUpdated.Builder(),
                new VertexPropertyAdded.Builder(),
                new VertexPropertyRemoved.Builder(),
                new VertexPropertyUpdated.Builder()
        );
    }

    static Stream<PropertyEvent.Builder> propertyEventsMissingValue() {
        return Stream.of(
                new AllEdgesPropertyAdded.Builder().name("name"),
                new AllEdgesPropertyRemoved.Builder().name("name"),
                new AllEdgesPropertyUpdated.Builder().name("name"),
                new AllVerticesPropertyAdded.Builder().name("name"),
                new AllVerticesPropertyRemoved.Builder().name("name"),
                new AllVerticesPropertyUpdated.Builder().name("name"),
                new EdgePropertyAdded.Builder().name("name"),
                new EdgePropertyRemoved.Builder().name("name"),
                new EdgePropertyUpdated.Builder().name("name"),
                new GraphPropertyAdded.Builder().name("name"),
                new GraphPropertyRemoved.Builder().name("name"),
                new GraphPropertyUpdated.Builder().name("name"),
                new VertexPropertyAdded.Builder().name("name"),
                new VertexPropertyRemoved.Builder().name("name"),
                new VertexPropertyUpdated.Builder().name("name")
        );
    }

    @ParameterizedTest
    @MethodSource("propertyEventsMissingName")
    void eventsMissingPropertyName(PropertyEvent.Builder builder) {
        Throwable thrown = assertThrows(NullPointerException.class, builder::build);
        assertThat(thrown).hasMessage("name must not be null.");
    }

    @ParameterizedTest
    @MethodSource("propertyEventsMissingValue")
    void eventsMissingPropertyValue(PropertyEvent.Builder builder) {
        Throwable thrown = assertThrows(NullPointerException.class, builder::build);
        assertThat(thrown).hasMessage("value must not be null.");
    }
}
