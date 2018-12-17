package publicapi.events;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.events.*;
import com.google.common.truth.Truth8;
import org.junit.jupiter.api.Test;

public class VertexEventBuilders {

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
    void testVertexCreated() {
        var event = new VertexCreated.Builder<String>()
            .graphId("graph")
            .vertexId("A")
            .build();

        Truth8.assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getVertexId()).isEqualTo("A");
    }

    @Test
    void testVertexRemoved() {
        var event = new VertexRemoved.Builder<String>()
            .graphId("graph")
            .vertexId("A")
            .build();

        Truth8.assertThat(event.getGraphId()).hasValue("graph");
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

        Truth8.assertThat(event.getGraphId()).hasValue("graph");
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

        Truth8.assertThat(event.getGraphId()).hasValue("graph");
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

        Truth8.assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getName()).isEqualTo("name");
        assertThat(event.getValue()).isEqualTo("value");
        assertThat(event.getOldValue()).isEqualTo("oldValue");
    }
}
