package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.Graph;
import testframework.SimpleGraphs;

public class GraphProperties {

    @SimpleGraphs
    void defaultId(Graph graph) {
        assertThat(graph).hasIdThat().isEmpty();
    }

    @SimpleGraphs
    void setId(Graph graph) {
        graph.setId("id");
        assertThat(graph).hasIdThat().hasValue("id");
    }

    @SimpleGraphs
    void id(Graph graph) {
        Graph next = graph.id("id");
        assertThat(graph).hasIdThat().hasValue("id");
        assertThat(graph).isSameAs(next);
    }

    @SimpleGraphs
    void defaultProperty(Graph graph) {
        assertThat(graph).withProperty("property").isEmpty();
    }

    @SimpleGraphs
    void setPropertyNullKey(Graph graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setProperty(null, null));
        assertThat(thrown).hasMessageThat().isEqualTo("name must not be null.");
    }

    @SimpleGraphs
    void setPropertyNullValue(Graph graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setProperty("property", null));
        assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
    }

    @SimpleGraphs
    void setPorpertyEmptyName(Graph graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.setProperty("", "value"));
        assertThat(thrown).hasMessageThat().isEqualTo("name must not be empty.");
    }

    @SimpleGraphs
    void setProperty(Graph graph) {
        graph.setProperty("property", "value");
        assertThat(graph).withProperty("property").hasValue("value");
    }

    @SimpleGraphs
    void property(Graph graph) {
        Graph next = graph.property("property", "value");
        assertThat(graph).withProperty("property").hasValue("value");
        assertThat(next).isSameAs(graph);
    }

    @SimpleGraphs
    void removePropertyMissing(Graph graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeProperty("property"));
        assertThat(thrown).hasMessageThat().isEqualTo("graph does not contain property named 'property'.");
    }

    @SimpleGraphs
    void removeProperty(Graph graph) {
        graph.property("property", "value");
        graph.removeProperty("property");
        assertThat(graph).withProperty("property").isEmpty();
    }
}
