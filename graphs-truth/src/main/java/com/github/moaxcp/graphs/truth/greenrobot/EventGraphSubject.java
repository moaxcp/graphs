package com.github.moaxcp.graphs.truth.greenrobot;

import com.github.moaxcp.graphs.EventGraph;
import com.google.common.truth.Fact;
import com.google.common.truth.FailureMetadata;
import com.google.common.truth.IterableSubject;
import com.google.common.truth.Subject;
import org.greenrobot.eventbus.EventBus;

import java.util.function.Consumer;

import static com.google.common.truth.Truth.assertAbout;

public final class EventGraphSubject extends Subject {

    private EventBus bus;
    private EventGraph<String> actual;

    public static Subject.Factory<EventGraphSubject, EventGraph<String>> eventGraphs() {
        return EventGraphSubject::new;
    }

    public static EventGraphSubject assertThat(EventGraph<String> actual) {
        return assertAbout(eventGraphs()).that(actual);
    }

    protected EventGraphSubject(FailureMetadata metadata, EventGraph<String> actual) {
        super(metadata, actual);
        this.actual = actual;
    }

    public EventGraphSubject with(EventBus bus) {
        this.bus = bus;
        return this;
    }

    public IterableSubject hasEventsIn(Consumer<EventGraph<String>> action) {
        var check = new GraphEventCheck(actual);
        bus.register(check);
        action.accept(actual);
        bus.unregister(check);
        if(check.getEventClasses().isEmpty()) {
            failWithActual(Fact.simpleFact("action did not result in events."));
        }
        return check("events").that(check.getEventClasses());
    }

    public void hasNoEventsIn(Consumer<EventGraph<String>> action) {
        var check = new GraphEventCheck(actual);
        bus.register(check);
        action.accept(actual);
        bus.unregister(check);
        if(!check.getEventClasses().isEmpty()) {
            failWithActual(Fact.simpleFact("action had events: " + check.getEventClasses()));
        }
    }
}
