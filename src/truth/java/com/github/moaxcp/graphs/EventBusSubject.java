package com.github.moaxcp.graphs;

import static com.google.common.truth.Truth.assertAbout;
import com.google.common.truth.*;
import java.util.*;
import org.greenrobot.eventbus.*;

public class EventBusSubject extends Subject<EventBusSubject, EventBus> {
    private Runnable action;

    public class Subscriber {
        List<Object> events = new ArrayList<>();

        @Subscribe
        public void event(Object event) {
            events.add(event);
        }
    }

    private Subscriber subscriber = new Subscriber();

    public static Subject.Factory<EventBusSubject, EventBus> busses() {
        return EventBusSubject::new;
    }
    private EventBusSubject(FailureMetadata metadata,  EventBus actual) {
        super(metadata, actual);
    }

    public static IterableSubject assertEvents(Runnable action) {
        return assertAbout(busses()).that(EventBus.getDefault()).withAction(action);
    }

    public static EventBusSubject assertThat(EventBus actual) {
        return assertAbout(busses()).that(actual);
    }

    public IterableSubject withAction(Runnable action) {
        this.action = action;
        return runAction();
    }

    private IterableSubject runAction() {
        actual().register(subscriber);
        action.run();
        return check("events").that(subscriber.events);
    }
}
