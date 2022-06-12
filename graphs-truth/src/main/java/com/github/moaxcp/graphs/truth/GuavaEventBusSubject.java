package com.github.moaxcp.graphs.truth;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.common.truth.FailureMetadata;
import com.google.common.truth.IterableSubject;
import com.google.common.truth.Subject;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertAbout;

public class GuavaEventBusSubject extends Subject {
    private Runnable action;
    private EventBus actual;

    public class Subscriber {
        List<Object> events = new ArrayList<>();

        @Subscribe
        public void event(Object event) {
            events.add(event);
        }
    }

    private Subscriber subscriber = new Subscriber();

    public static Factory<GuavaEventBusSubject, EventBus> busses() {
        return GuavaEventBusSubject::new;
    }
    private GuavaEventBusSubject(FailureMetadata metadata, EventBus actual) {
        super(metadata, actual);
        this.actual = actual;
    }

    public static GuavaEventBusSubject assertThat(EventBus actual) {
        return assertAbout(busses()).that(actual);
    }

    public IterableSubject withAction(Runnable action) {
        this.action = action;
        return runAction();
    }

    private IterableSubject runAction() {
        actual.register(subscriber);
        action.run();
        actual.unregister(subscriber);
        return check("events").that(subscriber.events);
    }
}
