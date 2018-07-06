package com.github.moaxcp.graphs.event;

public abstract class Event {
    /**
     * @throws IllegalStateException when this GraphEven is invalid.
     */
    public abstract void checkEvent();
}
