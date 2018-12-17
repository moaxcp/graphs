package publicapi.events;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.events.*;
import com.google.common.truth.Truth8;
import org.junit.jupiter.api.Test;

public class EdgeEventBuilders {

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
    void edgeIdRemovedEventMissingOldEdgeId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdRemoved.Builder<String>().build());
        assertThat(thrown).hasMessageThat().isEqualTo("oldEdgeId must not be null.");
    }

    @Test
    void edgeIdRemovedEventMissingFrom() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdRemoved.Builder<String>().oldEdgeId("edge").build());
        assertThat(thrown).hasMessageThat().isEqualTo("from must not be null.");
    }

    @Test
    void edgeIdRemovedEventMissingTo() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdRemoved.Builder<String>().oldEdgeId("edge").from("A").build());
        assertThat(thrown).hasMessageThat().isEqualTo("to must not be null.");
    }

    @Test
    void edgeIdUpdatedEventMissingEdgeId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdUpdated.Builder<String>().build());
        assertThat(thrown).hasMessageThat().isEqualTo("edgeId must not be null.");
    }

    @Test
    void edgeIdUpdatedEventMissingOldEdgeId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdUpdated.Builder<String>().edgeId("id1").build());
        assertThat(thrown).hasMessageThat().isEqualTo("oldEdgeId must not be null.");
    }

    @Test
    void edgeIdUpdatedEventMissingFrom() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdUpdated.Builder<String>().edgeId("id1").oldEdgeId("id2").build());
        assertThat(thrown).hasMessageThat().isEqualTo("from must not be null.");
    }

    @Test
    void edgeIdUpdatedEventMissingTo() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdUpdated.Builder<String>().edgeId("id1").oldEdgeId("id2").from("A").build());
        assertThat(thrown).hasMessageThat().isEqualTo("to must not be null.");
    }

    @Test
    void testEdgeCreated() {
        var event = new EdgeCreated.Builder<String>()
            .graphId("graph")
            .edgeId("edge")
            .from("A")
            .to("B")
            .build();
        Truth8.assertThat(event.getGraphId()).hasValue("graph");
        Truth8.assertThat(event.getEdgeId()).hasValue("edge");
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
        Truth8.assertThat(event.getGraphId()).hasValue("graph");
        Truth8.assertThat(event.getEdgeId()).hasValue("edge");
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
        Truth8.assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getEdgeId()).isEqualTo("edge");
        assertThat(event.getFrom()).isEqualTo("A");
        assertThat(event.getTo()).isEqualTo("B");
    }

    @Test
    void testEdgeIdRemoved() {
        var event = new EdgeIdRemoved.Builder<String>()
            .graphId("graph")
            .oldEdgeId("edge")
            .from("A")
            .to("B")
            .build();
        Truth8.assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getOldEdgeId()).isEqualTo("edge");
        assertThat(event.getFrom()).isEqualTo("A");
        assertThat(event.getTo()).isEqualTo("B");
    }

    @Test
    void testEdgeIdUpdated() {
        var event = new EdgeIdUpdated.Builder<String>()
            .graphId("graph")
            .edgeId("id1")
            .oldEdgeId("id2")
            .from("A")
            .to("B")
            .build();
        Truth8.assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getEdgeId()).isEqualTo("id1");
        assertThat(event.getOldEdgeId()).isEqualTo("id2");
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

        Truth8.assertThat(event.getGraphId()).hasValue("graph");
        Truth8.assertThat(event.getEdgeId()).hasValue("edge");
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

        Truth8.assertThat(event.getGraphId()).hasValue("graph");
        Truth8.assertThat(event.getEdgeId()).hasValue("edge");
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

        Truth8.assertThat(event.getGraphId()).hasValue("graph");
        Truth8.assertThat(event.getEdgeId()).hasValue("edge");
        assertThat(event.getFrom()).isEqualTo("A");
        assertThat(event.getTo()).isEqualTo("B");
        assertThat(event.getName()).isEqualTo("name");
        assertThat(event.getValue()).isEqualTo("value");
        assertThat(event.getOldValue()).isEqualTo("oldValue");
    }
}
