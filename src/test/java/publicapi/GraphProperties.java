package publicapi;

import static com.github.moaxcp.graphs.Truth.assertThat;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.github.moaxcp.graphs.SimpleGraph;
import testframework.SimpleGraphs;

public class GraphProperties {

    @SimpleGraphs
    void defaultId(SimpleGraph graph) {
        assertThat(graph).hasIdThat().isEmpty();
    }

    @SimpleGraphs
    void setId(SimpleGraph graph) {
        graph.setId("id");
        assertThat(graph).hasIdThat().hasValue("id");
    }

    @SimpleGraphs
    void id(SimpleGraph graph) {
        SimpleGraph next = graph.id("id");
        assertThat(graph).hasIdThat().hasValue("id");
        assertThat(graph).isSameAs(next);
    }

    @SimpleGraphs
    void defaultProperty(SimpleGraph graph) {
        assertThat(graph).hasPropertyThat("property").isEmpty();
    }

    @SimpleGraphs
    void setPropertyNullKey(SimpleGraph graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setProperty(null, null));
        assertThat(thrown).hasMessageThat().isEqualTo("name must not be null.");
    }

    @SimpleGraphs
    void setPropertyNullValue(SimpleGraph graph) {
        Throwable thrown = assertThrows(NullPointerException.class, () -> graph.setProperty("property", null));
        assertThat(thrown).hasMessageThat().isEqualTo("value must not be null.");
    }

    @SimpleGraphs
    void setProperty(SimpleGraph graph) {
        graph.setProperty("property", "value");
        assertThat(graph).hasPropertyThat("property").hasValue("value");
    }

    @SimpleGraphs
    void property(SimpleGraph graph) {
        SimpleGraph next = graph.property("property", "value");
        assertThat(graph).hasPropertyThat("property").hasValue("value");
        assertThat(next).isSameAs(graph);
    }

    @SimpleGraphs
    void removePropertyMissing(SimpleGraph graph) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> graph.removeProperty("property"));
        assertThat(thrown).hasMessageThat().isEqualTo("graph does not contain property named 'property'.");
    }
}
