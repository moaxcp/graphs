package com.github.moaxcp.graphs;

import static com.google.common.truth.Truth.assertAbout;
import com.google.common.truth.*;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class EventGraphSubject extends Subject<EventGraphSubject, EventGraph> {

    public static Subject.Factory<EventGraphSubject, EventGraph> eventGraphs() {
        return EventGraphSubject::new;
    }

    static EventGraphSubject assertThat(@Nullable EventGraph actual) {
        return assertAbout(eventGraphs()).that(actual);
    }

    /**
     * Constructor for use by subclasses. If you want to create an instance of this class itself, call
     * {@link Subject#check}{@code .that(actual)}.
     *
     * @param metadata
     * @param actual
     */
    protected EventGraphSubject(FailureMetadata metadata, @NullableDecl EventGraph actual) {
        super(metadata, actual);
    }

    public IterableSubject hasEventsIn(Consumer<EventGraph> action) {
        var check = new GraphEventCheck(actual());
        actual().getBus().register(check);
        action.accept(actual());
        actual().getBus().unregister(check);
        if(check.getEventClasses().isEmpty()) {
            failWithActual(Fact.simpleFact("action did not result in events."));
        }
        return check("events").that(check.getEventClasses());
    }

    public void hasNoEventsIn(Consumer<EventGraph> action) {
        var check = new GraphEventCheck(actual());
        actual().getBus().register(check);
        action.accept(actual());
        actual().getBus().unregister(check);
        if(!check.getEventClasses().isEmpty()) {
            failWithActual(Fact.simpleFact("action had events: " + check.getEventClasses()));
        }
    }
}
