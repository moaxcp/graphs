package publicapi.graph;

import com.github.moaxcp.graphs.Graph;
import org.greenrobot.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import stubs.TestEventSubscriber;

import static com.google.common.truth.Truth.assertThat;

public class GraphCreatedEvents {
    @Test
    void emptyConstructor() {
        var subscriber = new TestEventSubscriber();
        EventBus.getDefault().register(subscriber);
        new Graph();

        assertThat(subscriber.getAllEvents()).hasSize(1);
    }
}
