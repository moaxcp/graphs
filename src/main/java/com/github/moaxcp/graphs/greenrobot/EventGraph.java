package com.github.moaxcp.graphs.greenrobot;

import com.github.moaxcp.graphs.Graph;
import org.greenrobot.eventbus.EventBus;

public interface EventGraph<T> extends Graph<T> {
    EventBus getBus();
}
