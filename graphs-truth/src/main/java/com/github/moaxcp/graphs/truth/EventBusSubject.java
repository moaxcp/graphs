package com.github.moaxcp.graphs.truth;

import com.google.common.truth.FailureMetadata;
import com.google.common.truth.IterableSubject;
import com.google.common.truth.Subject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertAbout;

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

    static IterableSubject assertEvents(Runnable action) {
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
