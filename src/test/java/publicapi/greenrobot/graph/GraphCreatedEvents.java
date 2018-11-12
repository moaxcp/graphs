package publicapi.greenrobot.graph;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import stubs.TestEventSubscriber;

import static com.google.common.truth.Truth.assertThat;

public class GraphCreatedEvents {
    @Test
    void emptyConstructor() {
        var subscriber = new TestEventSubscriber();
        EventBus.getDefault().register(subscriber);
        new UndirectedGraph();

        assertThat(subscriber.getAllEvents()).hasSize(1);
    }
}
