package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import com.github.moaxcp.graphs.*;
import org.junit.jupiter.api.Test;

public class GraphId {
    @Test
    void undirectedSimpleGraph() {
        SimpleGraph graph = new UndirectedGraph("id");
        assertThat(graph).hasIdThat().hasValue("id");
    }

    @Test
    void directedSimpleGraph() {
        SimpleGraph graph = new DirectedGraph("id");
        assertThat(graph).hasIdThat().hasValue("id");
    }
}
