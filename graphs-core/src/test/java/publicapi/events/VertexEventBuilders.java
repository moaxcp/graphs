package publicapi.events;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.events.*;
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
    void vertexIdUpdatedMissingId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new VertexIdUpdated.Builder<String>().build());
        assertThat(thrown).hasMessageThat().isEqualTo("vertexId must not be null.");
    }

    @Test
    void vertexIdUpdatedMissingOldId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new VertexIdUpdated.Builder<String>().vertexId("id1").build());
        assertThat(thrown).hasMessageThat().isEqualTo("oldVertexId must not be null.");
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
    void testVertexIdUpdated() {
        var event = new VertexIdUpdated.Builder<String>()
            .graphId("graph")
            .vertexId("id1")
            .oldVertexId("id2")
            .build();

        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getVertexId()).isEqualTo("id1");
        assertThat(event.getOldVertexId()).isEqualTo("id2");
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
