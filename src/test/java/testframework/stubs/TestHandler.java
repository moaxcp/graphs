package testframework.stubs;

import com.github.moaxcp.graphs.event.GraphEvent;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class TestHandler {
    public GraphEvent event;
    public List<GraphEvent> events = new ArrayList<>();
    @Subscribe
    public void handle(GraphEvent event) {
        this.event = event;
        events.add(event);
    }

    public GraphEvent getEvent() {
        return event;
    }

    public List<GraphEvent> getEvents() {
        return events;
    }
}
