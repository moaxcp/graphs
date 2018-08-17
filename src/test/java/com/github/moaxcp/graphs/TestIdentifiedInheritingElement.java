package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.event.*;
import org.greenrobot.eventbus.EventBus;

import java.util.Map;

public class TestIdentifiedInheritingElement extends IdentifiedInheritingElement {
    public TestIdentifiedInheritingElement(String id, Map<String, Object> inherited, EventBus bus) {
        super(id, inherited, bus);
    }
    @Override
    public TestIdentifiedInheritingElement withProperty(String name, Object value) {
        setProperty(name, value);
        return this;
    }

    @Override
    protected PropertyAddedGraphEvent propertyAddedEvent(String name, Object value) {
        return new GraphPropertyAddedGraphEvent().withName(name).withValue(value);
    }

    @Override
    protected PropertyRemovedGraphEvent propertyRemovedEvent(String name, Object value) {
        return new GraphPropertyRemovedGraphEvent().withName(name).withValue(value);
    }

    @Override
    protected PropertyUpdatedGraphEvent propertyUpdatedEvent(String name, Object value, Object oldValue) {
        return new GraphPropertyUpdatedGraphEvent().withName(name).withValue(value).withOldValue(oldValue);    }
}
