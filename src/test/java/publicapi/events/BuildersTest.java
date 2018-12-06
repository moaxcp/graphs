package publicapi.events;

import static com.github.moaxcp.graphs.events.Builders.*;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.events.PropertyEvent.Builder;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class BuildersTest {

    static Stream<Builder> propertyEventsMissingName() {
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

    static Stream<Builder> propertyEventsMissingValue() {
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
    void eventsMissingPropertyName(Builder builder) {
        Throwable thrown = assertThrows(NullPointerException.class, builder::build);
        assertThat(thrown).hasMessage("name must not be null.");
    }

    @ParameterizedTest
    @MethodSource("propertyEventsMissingValue")
    void eventsMissingPropertyValue(Builder builder) {
        Throwable thrown = assertThrows(NullPointerException.class, builder::build);
        assertThat(thrown).hasMessage("value must not be null.");
    }

    @ParameterizedTest
    @MethodSource("propertyUpdatedEventsMissingOldValue")
    void eventsMissingPropertyOldValue(Builder builder) {
        Throwable thrown = assertThrows(NullPointerException.class, builder::build);
        assertThat(thrown).hasMessage("oldValue must not be null.");
    }

    @Test
    void vertexCreatedEventMissingId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> vertexCreated().build());
        assertThat(thrown).hasMessageThat().isEqualTo("vertexId must not be null.");
    }

    @Test
    void vertexRemovedEventMissingId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> vertexRemoved().build());
        assertThat(thrown).hasMessageThat().isEqualTo("vertexId must not be null.");
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

    @Test
    void testDirectedGraphCreated() {
        var event = DirectedGraphCreated.builder()
                .graphId("graph")
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
    }

    @Test
    void testUndirectedGraphCreated() {
        var event = UndirectedGraphCreated.builder()
                .graphId("graph")
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
    }

    @Test
    void testEdgeCreated() {
        var event = EdgeCreated.builder()
                .graphId("graph")
                .edgeId("edge")
                .from("A")
                .to("B")
                .build();
        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getEdgeId()).hasValue("edge");
        assertThat(event.getFrom()).isEqualTo("A");
        assertThat(event.getTo()).isEqualTo("B");
    }

    @Test
    void testEdgeRemoved() {
        var event = EdgeRemoved.builder()
                .graphId("graph")
                .edgeId("edge")
                .from("A")
                .to("B")
                .build();
        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getEdgeId()).hasValue("edge");
        assertThat(event.getFrom()).isEqualTo("A");
        assertThat(event.getTo()).isEqualTo("B");
    }

    @Test
    void testEdgePropertyAdded() {
        var event = EdgePropertyAdded.builder()
                .graphId("graph")
                .edgeId("edge")
                .from("A")
                .to("B")
                .name("name")
                .value("value")
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getEdgeId()).hasValue("edge");
        assertThat(event.getFrom()).isEqualTo("A");
        assertThat(event.getTo()).isEqualTo("B");
        assertThat(event.getName()).isEqualTo("name");
        assertThat(event.getValue()).isEqualTo("value");
    }

    @Test
    void testEdgePropertyRemoved() {
        var event = EdgePropertyRemoved.builder()
                .graphId("graph")
                .edgeId("edge")
                .from("A")
                .to("B")
                .name("name")
                .value("value")
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getEdgeId()).hasValue("edge");
        assertThat(event.getFrom()).isEqualTo("A");
        assertThat(event.getTo()).isEqualTo("B");
        assertThat(event.getName()).isEqualTo("name");
        assertThat(event.getValue()).isEqualTo("value");
    }

    @Test
    void testEdgePropertyUpdated() {
        var event = EdgePropertyUpdated.builder()
                .graphId("graph")
                .edgeId("edge")
                .from("A")
                .to("B")
                .name("name")
                .value("value")
                .oldValue("oldValue")
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getEdgeId()).hasValue("edge");
        assertThat(event.getFrom()).isEqualTo("A");
        assertThat(event.getTo()).isEqualTo("B");
        assertThat(event.getName()).isEqualTo("name");
        assertThat(event.getValue()).isEqualTo("value");
        assertThat(event.getOldValue()).isEqualTo("oldValue");
    }

    @Test
    void testGraphPropertyAdded() {
        var event = GraphPropertyAdded.builder()
                .graphId("graph")
                .name("name")
                .value("value")
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getName()).isEqualTo("name");
        assertThat(event.getValue()).isEqualTo("value");
    }

    @Test
    void testGraphPropertyRemoved() {
        var event = GraphPropertyRemoved.builder()
                .graphId("graph")
                .name("name")
                .value("value")
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getName()).isEqualTo("name");
        assertThat(event.getValue()).isEqualTo("value");
    }

    @Test
    void testGraphPropertyUpdated() {
        var event = GraphPropertyUpdated.builder()
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
    void testVertexCreated() {
        var event = VertexCreated.builder()
                .graphId("graph")
                .vertexId("A")
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getVertexId()).isEqualTo("A");
    }

    @Test
    void testVertexRemoved() {
        var event = VertexRemoved.builder()
                .graphId("graph")
                .vertexId("A")
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getVertexId()).isEqualTo("A");
    }

    @Test
    void testVertexPropertyAdded() {
        var event = VertexPropertyAdded.builder()
                .graphId("graph")
                .vertexId("A")
                .name("name")
                .value("value")
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getVertexId()).isEqualTo("A");
        assertThat(event.getName()).isEqualTo("name");
        assertThat(event.getValue()).isEqualTo("value");
    }

    @Test
    void testVertexPropertyRemoved() {
        var event = VertexPropertyRemoved.builder()
                .graphId("graph")
                .vertexId("A")
                .name("name")
                .value("value")
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getName()).isEqualTo("name");
        assertThat(event.getValue()).isEqualTo("value");
    }

    @Test
    void testVertexPropertyUpdated() {
        var event = VertexPropertyUpdated.builder()
                .graphId("graph")
                .vertexId("A")
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
