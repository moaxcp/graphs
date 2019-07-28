package com.github.moaxcp.graphs.truth;

import static com.google.common.truth.Fact.simpleFact;
import static com.google.common.truth.OptionalSubject.*;
import static com.google.common.truth.Truth.*;
import com.github.moaxcp.graphs.Graph.*;
import com.google.common.truth.*;
import java.util.*;

public class EdgeSubject extends Subject<EdgeSubject, Edge> {
    /**
     * Constructor for use by subclasses. If you want to create an instance of this class itself, call
     * {@link Subject#check}{@code .that(actual)}.
     *
     * @param metadata
     * @param actual
     */
    private EdgeSubject(FailureMetadata metadata, Edge actual) {
        super(metadata, actual);
    }

    public static Factory<EdgeSubject, Edge> edges() {
        return EdgeSubject::new;
    }

    static EdgeSubject assertThat(Edge actual) {
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

    public OptionalSubject withProperty(String name) {
        Optional<Object> optional = actual().getProperty(name);
        return check("getProperty(%s)", name).about(optionals()).that(optional);
    }

    public Subject<DefaultSubject, Object> withLocalProperty(String name) {
        return check("getLocal(%s)", name).that(actual().local().get(name));
    }

    public MapSubject withLocal() {
        return check("local()").that(actual().local());
    }

    public MapSubject withInherited() {
        return check("inherited()").that(actual().inherited());
    }

    public void isDirected() {
        if(!actual().isDirected()) {
            failWithActual(simpleFact("Expected directed edge."));
        }
    }

    public void isNotDirected() {
        if(actual().isDirected()) {
            failWithActual(simpleFact("Expected undirected edge."));
        }
    }
}
