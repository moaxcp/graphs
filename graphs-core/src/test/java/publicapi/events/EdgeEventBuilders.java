package publicapi.events;

import com.github.moaxcp.graphs.events.*;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void edgeIdAddedEventMissingFrom() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdAdded.Builder<String>().build());
        assertThat(thrown).hasMessageThat().isEqualTo("from must not be null.");
    }

    @Test
    void edgeIdAddedEventMissingTo() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdAdded.Builder<String>().edgeId("edge").from("A").build());
        assertThat(thrown).hasMessageThat().isEqualTo("to must not be null.");
    }

    @Test
    void edgeIdAddedEventMissingId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdAdded.Builder<String>().from("A").to("B").build());
        assertThat(thrown).hasMessageThat().isEqualTo("edgeId must not be null.");
    }

    @Test
    void edgeIdRemovedEventMissingFrom() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdRemoved.Builder<String>().build());
        assertThat(thrown).hasMessageThat().isEqualTo("from must not be null.");
    }

    @Test
    void edgeIdRemovedEventMissingTo() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdRemoved.Builder<String>().from("A").build());
        assertThat(thrown).hasMessageThat().isEqualTo("to must not be null.");
    }

    @Test
    void edgeIdRemovedEventMissingEdgeId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdRemoved.Builder<String>().from("A").to("B").build());
        assertThat(thrown).hasMessageThat().isEqualTo("edgeId must not be null.");
    }

    @Test
    void edgeIdUpdatedEventMissingFrom() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdUpdated.Builder<String>().build());
        assertThat(thrown).hasMessageThat().isEqualTo("from must not be null.");
    }

    @Test
    void edgeIdUpdatedEventMissingTo() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdUpdated.Builder<String>().from("A").build());
        assertThat(thrown).hasMessageThat().isEqualTo("to must not be null.");
    }

    @Test
    void edgeIdUpdatedEventMissingEdgeId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdUpdated.Builder<String>().from("from").to("to").build());
        assertThat(thrown).hasMessageThat().isEqualTo("edgeId must not be null.");
    }

    @Test
    void edgeIdUpdatedEventMissingOldEdgeId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeIdUpdated.Builder<String>().from("from").to("to").edgeId("id1").build());
        assertThat(thrown).hasMessageThat().isEqualTo("oldEdgeId must not be null.");
    }

    @Test
    void edgeToUpdatedEventMissingOldEdgeTo() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeToUpdated.Builder<String>().from("from").to("to").edgeId("id1").build());
        assertThat(thrown).hasMessageThat().isEqualTo("oldTo must not be null.");
    }

    @Test
    void edgeFromUpdatedEventMissingOldEdgeTo() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new EdgeFromUpdated.Builder<String>().from("from").to("to").edgeId("id1").build());
        assertThat(thrown).hasMessageThat().isEqualTo("oldFrom must not be null.");
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
    void testEdgeIdRemoved() {
        var event = new EdgeIdRemoved.Builder<String>()
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
    void testEdgeIdUpdated() {
        var event = new EdgeIdUpdated.Builder<String>()
            .graphId("graph")
            .edgeId("id1")
            .oldEdgeId("id2")
            .from("A")
            .to("B")
            .build();
        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getEdgeId()).isEqualTo("id1");
        assertThat(event.getOldEdgeId()).isEqualTo("id2");
        assertThat(event.getFrom()).isEqualTo("A");
        assertThat(event.getTo()).isEqualTo("B");
    }

    @Test
    void testEdgeToUpdated() {
        var event = new EdgeToUpdated.Builder<String>()
                .graphId("graph")
                .edgeId("id")
                .from("A")
                .to("B")
                .oldTo("oldTo")
                .build();
        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getEdgeId()).hasValue("id");
        assertThat(event.getFrom()).isEqualTo("A");
        assertThat(event.getTo()).isEqualTo("B");
        assertThat(event.getOldTo()).isEqualTo("oldTo");
    }

    @Test
    void testEdgeFromUpdated() {
        var event = new EdgeFromUpdated.Builder<String>()
                .graphId("graph")
                .edgeId("id")
                .from("A")
                .to("B")
                .oldFrom("oldFrom")
                .build();
        assertThat(event.getGraphId()).hasValue("graph");
        assertThat(event.getEdgeId()).hasValue("id");
        assertThat(event.getFrom()).isEqualTo("A");
        assertThat(event.getTo()).isEqualTo("B");
        assertThat(event.getOldFrom()).isEqualTo("oldFrom");
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
}
