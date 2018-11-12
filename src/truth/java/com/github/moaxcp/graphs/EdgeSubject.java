package com.github.moaxcp.graphs;

import com.github.moaxcp.graphs.greenrobot.UndirectedGraph;
import com.google.common.truth.DefaultSubject;
import com.google.common.truth.FailureMetadata;
import com.google.common.truth.OptionalSubject;
import com.google.common.truth.Subject;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Optional;

import static com.google.common.truth.OptionalSubject.optionals;
import static com.google.common.truth.Truth.assertAbout;

public class EdgeSubject extends Subject<EdgeSubject, UndirectedGraph.Edge> {
    /**
     * Constructor for use by subclasses. If you want to create an instance of this class itself, call
     * {@link Subject#check}{@code .that(actual)}.
     *
     * @param metadata
     * @param actual
     */
    private EdgeSubject(FailureMetadata metadata, @NullableDecl UndirectedGraph.Edge actual) {
        super(metadata, actual);
    }

    public static Factory<EdgeSubject, UndirectedGraph.Edge> edges() {
        return EdgeSubject::new;
    }

    public static EdgeSubject assertThat(@Nullable UndirectedGraph.Edge actual) {
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
