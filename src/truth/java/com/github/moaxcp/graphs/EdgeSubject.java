package com.github.moaxcp.graphs;

import com.google.common.truth.DefaultSubject;
import com.google.common.truth.FailureMetadata;
import com.google.common.truth.OptionalSubject;
import com.google.common.truth.Subject;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Optional;

import static com.google.common.truth.Fact.fact;
import static com.google.common.truth.OptionalSubject.optionals;
import static com.google.common.truth.Truth.assertAbout;

public class EdgeSubject extends Subject<EdgeSubject, Graph.Edge> {
    /**
     * Constructor for use by subclasses. If you want to create an instance of this class itself, call
     * {@link Subject#check}{@code .that(actual)}.
     *
     * @param metadata
     * @param actual
     */
    private EdgeSubject(FailureMetadata metadata, @NullableDecl Graph.Edge actual) {
        super(metadata, actual);
    }

    public static Factory<EdgeSubject, Graph.Edge> edges() {
        return EdgeSubject::new;
    }

    public static EdgeSubject assertThat(@Nullable Graph.Edge actual) {
        return assertAbout(edges()).that(actual);
    }

    public OptionalSubject id() {
        return check("getId()").about(optionals()).that(actual().getId());
    }

    public OptionalSubject thatProperty(String name) {
        Optional<Object> optional = actual().getProperty(name);
        return check("getProperty(%s)", name).about(optionals()).that(optional);
    }

    public Subject<DefaultSubject, Object> thatLocal(String name) {
        return check("getLocal(%s)", name).that(actual().getLocal().get(name));
    }
}
