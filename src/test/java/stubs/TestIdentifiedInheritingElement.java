package stubs;

import com.github.moaxcp.graphs.IdentifiedInheritingElement;
import com.github.moaxcp.graphs.event.*;
import org.greenrobot.eventbus.EventBus;

import java.util.Map;

public class TestIdentifiedInheritingElement extends IdentifiedInheritingElement<TestIdentifiedInheritingElement> {
    public TestIdentifiedInheritingElement(String id, Map<String, Object> inherited, EventBus bus) {
        super(id, inherited, bus);
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
