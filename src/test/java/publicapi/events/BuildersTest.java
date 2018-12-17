package publicapi.events;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.events.*;
import com.github.moaxcp.graphs.events.GraphEvent.Builder;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class BuildersTest {

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

    @Test
    void vertexCreatedEventMissingId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new VertexCreated.Builder<String>().build());
        assertThat(thrown).hasMessageThat().isEqualTo("vertexId must not be null.");
    }

    @Test
    void vertexRemovedEventMissingId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new VertexRemoved.Builder<String>().build());
        assertThat(thrown).hasMessageThat().isEqualTo("vertexId must not be null.");
    }

    @Test
    void edgeCreatedEventMissingFrom() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeCreated.Builder<String>().build());
        assertThat(thrown).hasMessageThat().isEqualTo("from must not be null.");
    }

    @Test
    void edgeCreatedEventMissingTo() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeCreated.Builder<String>().from("A").build());
        assertThat(thrown).hasMessageThat().isEqualTo("to must not be null.");
    }

    @Test
    void edgeRemovedEventMissingFrom() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeRemoved.Builder<String>().build());
        assertThat(thrown).hasMessageThat().isEqualTo("from must not be null.");
    }

    @Test
    void edgeRemovedEventMissingTo() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeRemoved.Builder<String>().from("A").build());
        assertThat(thrown).hasMessageThat().isEqualTo("to must not be null.");
    }

    @Test
    void edgeIdAddedEventMissingId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdAdded.Builder<String>().build());
        assertThat(thrown).hasMessageThat().isEqualTo("edgeId must not be null.");
    }

    @Test
    void edgeIdAddedEventMissingFrom() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdAdded.Builder<String>().edgeId("edge").build());
        assertThat(thrown).hasMessageThat().isEqualTo("from must not be null.");
    }

    @Test
    void edgeIdAddedEventMissingTo() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdAdded.Builder<String>().edgeId("edge").from("A").build());
        assertThat(thrown).hasMessageThat().isEqualTo("to must not be null.");
    }

    @Test
    void testAllEdgesPropertyAdded() {
        var event = new AllEdgesPropertyAdded.Builder<String>()
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
        var event = new AllEdgesPropertyRemoved.Builder<String>()
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
        var event = new AllEdgesPropertyUpdated.Builder<String>()
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
        var event = new AllVerticesPropertyAdded.Builder<String>()
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
        var event = new AllVerticesPropertyRemoved.Builder<String>()
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
        var event = new AllVerticesPropertyUpdated.Builder<String>()
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
        var event = new DirectedGraphCreated.Builder<String>()
                .graphId("graph")
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
    }

    @Test
    void testUndirectedGraphCreated() {
        var event = new UndirectedGraphCreated.Builder<String>()
                .graphId("graph")
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
    }

    @Test
    void testEdgeCreated() {
        var event = new EdgeCreated.Builder<String>()
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
        var event = new EdgeRemoved.Builder<String>()
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
    void testEdgeIdAdded() {
        var event = new EdgeIdAdded.Builder<String>()
            .graphId("graph")
            .edgeId("edge")
            .from("A")
            .to("B")
            .build();
        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getEdgeId()).isEqualTo("edge");
        assertThat(event.getFrom()).isEqualTo("A");
        assertThat(event.getTo()).isEqualTo("B");
    }

    @Test
    void testEdgePropertyAdded() {
        var event = new EdgePropertyAdded.Builder<String>()
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
        var event = new EdgePropertyRemoved.Builder<String>()
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
        var event = new EdgePropertyUpdated.Builder<String>()
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
        var event = new GraphPropertyAdded.Builder<String>()
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
        var event = new GraphPropertyRemoved.Builder<String>()
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
        var event = new GraphPropertyUpdated.Builder<String>()
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
        var event = new VertexCreated.Builder<String>()
                .graphId("graph")
                .vertexId("A")
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getVertexId()).isEqualTo("A");
    }

    @Test
    void testVertexRemoved() {
        var event = new VertexRemoved.Builder<String>()
                .graphId("graph")
                .vertexId("A")
                .build();

        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getVertexId()).isEqualTo("A");
    }

    @Test
    void testVertexPropertyAdded() {
        var event = new VertexPropertyAdded.Builder<String>()
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
        var event = new VertexPropertyRemoved.Builder<String>()
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
        var event = new VertexPropertyUpdated.Builder<String>()
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
