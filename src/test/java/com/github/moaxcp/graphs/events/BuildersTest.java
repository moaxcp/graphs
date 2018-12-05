package com.github.moaxcp.graphs.events;

import static com.github.moaxcp.graphs.events.Builders.*;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class BuildersTest {

    static Stream<PropertyEvent.Builder> propertyEventsMissingName() {
        return Stream.of(
                allEdgesPropertyAdded(),
                allEdgesPropertyRemoved(),
                allEdgesPropertyUpdated(),
                allVerticesPropertyAdded(),
                allVerticesPropertyRemoved(),
                allVerticesPropertyUpdated(),
                edgePropertyAdded(),
                edgePropertyRemoved(),
                edgePropertyUpdated(),
                graphPropertyAdded(),
                graphPropertyRemoved(),
                graphPropertyUpdated(),
                vertexPropertyAdded(),
                vertexPropertyRemoved(),
                vertexProeprtyUpdated()
        );
    }

    static Stream<PropertyEvent.Builder> propertyEventsMissingValue() {
        return Stream.of(
                allEdgesPropertyAdded().name("name"),
                allEdgesPropertyRemoved().name("name"),
                allEdgesPropertyUpdated().name("name"),
                allVerticesPropertyAdded().name("name"),
                allVerticesPropertyRemoved().name("name"),
                allVerticesPropertyUpdated().name("name"),
                edgePropertyAdded().name("name"),
                edgePropertyRemoved().name("name"),
                edgePropertyUpdated().name("name"),
                graphPropertyAdded().name("name"),
                graphPropertyRemoved().name("name"),
                graphPropertyUpdated().name("name"),
                vertexPropertyAdded().name("name"),
                vertexPropertyRemoved().name("name"),
                vertexProeprtyUpdated().name("name")
        );
    }

    static Stream<PropertyUpdatedEvent.Builder> propertyUpdatedEventsMissingOldValue() {
        return Stream.of(
                allEdgesPropertyUpdated().name("name").value("value"),
                allVerticesPropertyUpdated().name("name").value("value"),
                graphPropertyUpdated().name("name").value("value")
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

    @ParameterizedTest
    @MethodSource("propertyUpdatedEventsMissingOldValue")
    void eventsMissingPropertyOldValue(PropertyEvent.Builder builder) {
        Throwable thrown = assertThrows(NullPointerException.class, builder::build);
        assertThat(thrown).hasMessage("oldValue must not be null.");
    }

    @Test
    void testAllEdgesPropertyAdded() {
        var event = AllEdgesPropertyAdded.builder()
                .graphId("graph")
                .name("name")
                .value("value")
                .build();
        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getName()).isEqualTo("name");
        assertThat(event.getValue()).isEqualTo("value");
    }

    @Test
    void testAllEdgesPropertyRemoved() {
        var event = AllEdgesPropertyRemoved.builder()
                .graphId("graph")
                .name("name")
                .value("value")
                .build();
        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getName()).isEqualTo("name");
        assertThat(event.getValue()).isEqualTo("value");
    }

    @Test
    void testAllEdgesPropertyUpdated() {
        var event = AllEdgesPropertyUpdated.builder()
                .graphId("graph")
                .name("name")
                .value("value")
                .oldValue("oldValue")
                .build();
        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getName()).isEqualTo("name");
        assertThat(event.getValue()).isEqualTo("value");
        assertThat(event.getOldValue()).isEqualTo("oldValue");
    }

    @Test
    void testAllVerticesPropertyAdded() {
        var event = AllVerticesPropertyAdded.builder()
                .graphId("graph")
                .name("name")
                .value("value")
                .build();
        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getName()).isEqualTo("name");
        assertThat(event.getValue()).isEqualTo("value");
    }

    @Test
    void testAllVerticesPropertyRemoved() {
        var event = AllVerticesPropertyRemoved.builder()
                .graphId("graph")
                .name("name")
                .value("value")
                .build();
        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getName()).isEqualTo("name");
        assertThat(event.getValue()).isEqualTo("value");
    }

    @Test
    void testAllVerticesPropertyUpdated() {
        var event = AllVerticesPropertyUpdated.builder()
                .graphId("graph")
                .name("name")
                .value("value")
                .oldValue("oldValue")
                .build();
        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getName()).isEqualTo("name");
        assertThat(event.getValue()).isEqualTo("value");
        assertThat(event.getOldValue()).isEqualTo("oldValue");
    }
}
