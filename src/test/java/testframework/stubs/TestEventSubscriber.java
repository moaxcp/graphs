package testframework.stubs;

import com.github.moaxcp.graphs.newevents.GraphEvent;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestEventSubscriber {
    private GraphEvent latest;
    private List<GraphEvent> allEvents = new ArrayList<>();

    public GraphEvent getLatest() {
        return latest;
    }

    public List<GraphEvent> getAllEvents() {
        return Collections.unmodifiableList(allEvents);
    }

    @Subscribe
    public void event(GraphEvent event) {
        allEvents.add(event);
    }
}
