package com.github.moaxcp.graphs;

import static com.google.common.truth.OptionalSubject.*;
import static com.google.common.truth.Truth.*;
import com.github.moaxcp.graphs.SimpleGraph.*;
import com.google.common.truth.*;
import java.util.*;
import org.checkerframework.checker.nullness.compatqual.*;
import org.checkerframework.checker.nullness.qual.*;

public class EdgeSubject extends Subject<EdgeSubject, Edge> {
    /**
     * Constructor for use by subclasses. If you want to create an instance of this class itself, call
     * {@link Subject#check}{@code .that(actual)}.
     *
     * @param metadata
     * @param actual
     */
    private EdgeSubject(FailureMetadata metadata, @NullableDecl Edge actual) {
        super(metadata, actual);
    }

    public static Factory<EdgeSubject, Edge> edges() {
        return EdgeSubject::new;
    }

    public static EdgeSubject assertThat(@Nullable Edge actual) {
        return assertAbout(edges()).that(actual);
    }

    public OptionalSubject hasIdThat() {
        return check("getId()").about(optionals()).that(actual().getId());
    }

    public void hasNoId() {
        check("getId()").about(optionals()).that(actual().getId()).isEmpty();
    }

    public Subject<DefaultSubject, Object> hasFromThat() {
        return check("getFrom()").that(actual().getFrom());
    }

    public Subject<DefaultSubject, Object> hasToThat() {
        return check("getTo()").that(actual().getTo());
    }

    public OptionalSubject hasPropertyThat(String name) {
        Optional<Object> optional = actual().getProperty(name);
        return check("getProperty(%s)", name).about(optionals()).that(optional);
    }

    public Subject<DefaultSubject, Object> thatLocal(String name) {
        return check("getLocal(%s)", name).that(actual().getLocal().get(name));
    }
}
