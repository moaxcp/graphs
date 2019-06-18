package publicapi.events;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.events.*;
import org.junit.jupiter.api.Test;

public class GraphEventBuilders {

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
    void testGraphIdAddedMissingId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new GraphIdAdded.Builder<String>().build());
        assertThat(thrown).hasMessageThat().isEqualTo("graphId must not be null.");
    }

    @Test
    void testGraphIdAdded() {
        var event = new GraphIdAdded.Builder<String>()
            .graphId("graph")
            .build();

        assertThat(event.getGraphId()).isEqualTo("graph");
    }

    @Test
    void testGraphIdRemovedMissingId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new GraphIdRemoved.Builder<String>().build());
        assertThat(thrown).hasMessageThat().isEqualTo("graphId must not be null.");
    }

    @Test
    void testGraphIdRemoved() {
        var event = new GraphIdRemoved.Builder<String>()
            .graphId("graph")
            .build();

        assertThat(event.getGraphId()).isEqualTo("graph");
    }

    @Test
    void testGraphIdUpdatedMissingId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new GraphIdUpdated.Builder<String>().build());
        assertThat(thrown).hasMessageThat().isEqualTo("graphId must not be null.");
    }

    @Test
    void testGraphIdUpdatedMissingOldId() {
        Throwable thrown = assertThrows(NullPointerException.class, () -> new GraphIdUpdated.Builder<String>().graphId("graph").build());
        assertThat(thrown).hasMessageThat().isEqualTo("oldGraphId must not be null.");
    }

    @Test
    void testGraphIdUpdated() {
        var event = new GraphIdUpdated.Builder<String>()
            .graphId("id1")
            .oldGraphId("id2")
            .build();

        assertThat(event.getGraphId()).isEqualTo("id1");
        assertThat(event.getOldGraphId()).isEqualTo("id2");
    }
}
