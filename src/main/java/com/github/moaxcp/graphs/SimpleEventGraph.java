package com.github.moaxcp.graphs;

import org.greenrobot.eventbus.EventBus;

public interface SimpleEventGraph extends SimpleGraph {
    EventBus getBus();
}
