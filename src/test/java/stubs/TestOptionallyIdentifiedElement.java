package stubs;

import com.github.moaxcp.graphs.OptionallyIdentifiedElement;
import com.github.moaxcp.graphs.event.*;
import org.greenrobot.eventbus.EventBus;

public class TestOptionallyIdentifiedElement extends OptionallyIdentifiedElement<TestOptionallyIdentifiedElement> {
    public TestOptionallyIdentifiedElement(EventBus bus) {
        super(bus);
    }

    @Override
    protected TestOptionallyIdentifiedElement self() {
        return this;
    }

    @Override
    protected PropertyAddedGraphEvent propertyAddedEvent(String name, Object value) {
        return new GraphPropertyAddedGraphEvent().withName(name).withValue(value);
    }

    @Override
    protected PropertyRemovedGraphEvent propertyRemovedEvent(String name, Object value) {
        return new GraphPropertyRemovedGraphEvent().withName(name).withValue(value);    }

    @Override
    protected PropertyUpdatedGraphEvent propertyUpdatedEvent(String name, Object value, Object oldValue) {
        return new GraphPropertyUpdatedGraphEvent().withName(name).withValue(value).withOldValue(oldValue);    }
}
