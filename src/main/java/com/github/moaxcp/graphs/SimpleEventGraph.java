package com.github.moaxcp.graphs;

import org.greenrobot.eventbus.EventBus;

public interface SimpleEventGraph<T> extends SimpleGraph<T> {
    EventBus getBus();
}
