package com.github.moaxcp.graphs.truth;

import static com.google.common.truth.Fact.simpleFact;
import static com.google.common.truth.OptionalSubject.*;
import static com.google.common.truth.Truth.*;
import com.github.moaxcp.graphs.PropertyGraph.*;
import com.google.common.truth.*;
import java.util.*;

public class EdgeSubject extends Subject {

    private Edge actual;

    private EdgeSubject(FailureMetadata metadata, Edge actual) {
        super(metadata, actual);
        this.actual = actual;
    }

    public static Factory<EdgeSubject, Edge> edges() {
        return EdgeSubject::new;
    }

    static EdgeSubject assertThat(Edge actual) {
        return assertAbout(edges()).that(actual);
    }

    public OptionalSubject hasIdThat() {
        return check("getId()").about(optionals()).that(actual.getId());
    }

    public void hasNoId() {
        check("getId()").about(optionals()).that(actual.getId()).isEmpty();
    }

    public Subject hasFromThat() {
        return check("getFrom()").that(actual.getSource());
    }

    public Subject hasToThat() {
        return check("getTo()").that(actual.getTarget());
    }

    public OptionalSubject withProperty(String name) {
        Optional<Object> optional = actual.findProperty(name);
        return check("getProperty(%s)", name).about(optionals()).that(optional);
    }

    public Subject withLocalProperty(String name) {
        return check("getLocal(%s)", name).that(actual.local().get(name));
    }

    public MapSubject withLocal() {
        return check("local()").that(actual.local());
    }

    public MapSubject withInherited() {
        return check("inherited()").that(actual.inherited());
    }

    public void isDirected() {
        if(!actual.isDirected()) {
            failWithActual(simpleFact("Expected directed edge."));
        }
    }

    public void isNotDirected() {
        if(actual.isDirected()) {
            failWithActual(simpleFact("Expected undirected edge."));
        }
    }
}
