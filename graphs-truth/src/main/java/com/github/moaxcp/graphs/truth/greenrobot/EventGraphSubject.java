package com.github.moaxcp.graphs.truth.greenrobot;

import com.github.moaxcp.graphs.*;
import com.google.common.truth.*;
import org.greenrobot.eventbus.*;

import static com.google.common.truth.Truth.*;

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
}
