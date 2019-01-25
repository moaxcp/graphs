package com.github.moaxcp.graphs;

import org.greenrobot.eventbus.EventBus;

public interface EventGraph<T> extends Graph<T> {
    EventBus getBus();
}
