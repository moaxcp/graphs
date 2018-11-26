package publicapi.bus;

import static com.github.moaxcp.graphs.EventBusSubject.assertEvents;
import static com.github.moaxcp.graphs.events.Builders.directedGraphCreated;
import static com.github.moaxcp.graphs.events.Builders.undirectedGraphCreated;
import com.github.moaxcp.graphs.*;
import org.junit.jupiter.api.Test;

public class GraphCreated {
    @Test
    void undirectedEventGraphDefaultConstructor() {
        assertEvents(UndirectedEventGraph::new).containsExactly(undirectedGraphCreated().build());
    }

    @Test
    void undirectedEventGraphId() {
        assertEvents(() -> new UndirectedEventGraph("id")).containsExactly(undirectedGraphCreated().graphId("id").build());
    }

    @Test
    void directedEventGraphDefaultConstructor() {
        assertEvents(DirectedEventGraph::new).containsExactly(directedGraphCreated().build());
    }

    @Test
    void directedEventGraphId() {
        assertEvents(() -> new DirectedEventGraph("id")).containsExactly(directedGraphCreated().graphId("id").build());
    }
}
