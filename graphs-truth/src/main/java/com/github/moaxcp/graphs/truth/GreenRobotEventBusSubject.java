package com.github.moaxcp.graphs.truth;

import com.google.common.truth.FailureMetadata;
import com.google.common.truth.IterableSubject;
import com.google.common.truth.Subject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertAbout;

public class GreenRobotEventBusSubject extends Subject {
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

    public static Subject.Factory<GreenRobotEventBusSubject, EventBus> busses() {
        return GreenRobotEventBusSubject::new;
    }
    private GreenRobotEventBusSubject(FailureMetadata metadata, EventBus actual) {
        super(metadata, actual);
        this.actual = actual;
    }

    public static GreenRobotEventBusSubject assertThat(EventBus actual) {
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
