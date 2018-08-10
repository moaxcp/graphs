package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.event.GraphEvent;
import org.greenrobot.eventbus.Subscribe;

public class TestHandler {
    GraphEvent event;
    @Subscribe
    public void handle(TestEvent event) {
        this.event = event;
    }
}
