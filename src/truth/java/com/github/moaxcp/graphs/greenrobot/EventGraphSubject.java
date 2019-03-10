package com.github.moaxcp.graphs.greenrobot;

import static com.google.common.truth.Truth.assertAbout;

import com.github.moaxcp.graphs.EventGraph;
import com.github.moaxcp.graphs.GraphEventCheck;
import com.google.common.truth.*;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.greenrobot.eventbus.EventBus;

public final class EventGraphSubject extends Subject<EventGraphSubject, EventGraph<String>> {

    private EventBus bus;

    public static Subject.Factory<EventGraphSubject, EventGraph<String>> eventGraphs() {
        return EventGraphSubject::new;
    }

    public static EventGraphSubject assertThat(@Nullable EventGraph<String> actual) {
        return assertAbout(eventGraphs()).that(actual);
    }

    /**
     * Constructor for use by subclasses. If you want to create an instance of this class itself, call
     * {@link Subject#check}{@code .that(actual)}.
     *
     * @param metadata
     * @param actual
     */
    protected EventGraphSubject(FailureMetadata metadata, @NullableDecl EventGraph<String> actual) {
        super(metadata, actual);
    }

    public EventGraphSubject with(EventBus bus) {
        this.bus = bus;
        return this;
    }

    public IterableSubject hasEventsIn(Consumer<EventGraph<String>> action) {
        var check = new GraphEventCheck(actual());
        bus.register(check);
        action.accept(actual());
        bus.unregister(check);
        if(check.getEventClasses().isEmpty()) {
            failWithActual(Fact.simpleFact("action did not result in events."));
        }
        return check("events").that(check.getEventClasses());
    }

    public void hasNoEventsIn(Consumer<EventGraph<String>> action) {
        var check = new GraphEventCheck(actual());
        bus.register(check);
        action.accept(actual());
        bus.unregister(check);
        if(!check.getEventClasses().isEmpty()) {
            failWithActual(Fact.simpleFact("action had events: " + check.getEventClasses()));
        }
    }
}
