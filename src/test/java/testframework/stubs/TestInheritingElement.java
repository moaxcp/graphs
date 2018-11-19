package testframework.stubs;

import com.github.moaxcp.graphs.greenrobot.element.InheritingElement;
import com.github.moaxcp.graphs.event.*;
import org.greenrobot.eventbus.EventBus;

import java.util.Map;

public class TestInheritingElement extends InheritingElement<TestInheritingElement> {
    public TestInheritingElement(Map<String, Object> inherited, EventBus bus) {
        super(inherited, bus);
    }

    @Override
    protected TestInheritingElement self() {
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
