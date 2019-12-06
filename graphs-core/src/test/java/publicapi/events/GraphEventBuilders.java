package publicapi.events;

import com.github.moaxcp.graphs.events.*;
import org.junit.jupiter.api.*;

import static com.google.common.truth.Truth8.*;

public class GraphEventBuilders {

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
}
