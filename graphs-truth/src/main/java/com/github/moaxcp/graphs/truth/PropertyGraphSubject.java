package com.github.moaxcp.graphs.truth;

import com.github.moaxcp.graphs.PropertyGraph;
import com.github.moaxcp.graphs.PropertyGraph.Edge;
import com.github.moaxcp.graphs.PropertyGraph.Vertex;
import com.google.common.truth.FailureMetadata;
import com.google.common.truth.OptionalSubject;
import com.google.common.truth.Subject;

import java.util.*;

import static com.github.moaxcp.graphs.truth.EdgeSubject.edges;
import static com.github.moaxcp.graphs.truth.VertexSubject.vertices;
import static com.google.common.truth.Fact.simpleFact;
import static com.google.common.truth.OptionalSubject.optionals;
import static com.google.common.truth.Truth.assertAbout;

public class PropertyGraphSubject extends Subject {

    private PropertyGraph actual;

    PropertyGraphSubject(FailureMetadata metadata, PropertyGraph actual) {
        super(metadata, actual);
        this.actual = actual;
    }

    public static Subject.Factory<PropertyGraphSubject, PropertyGraph> graphs() {
        return PropertyGraphSubject::new;
    }

    static PropertyGraphSubject assertThat(PropertyGraph actual) {
        return assertAbout(graphs()).that(actual);
    }

    public OptionalSubject hasIdThat() {
        return check("getId()").about(optionals()).that(actual.getId());
    }

    public EdgeSubject hasEdge(Object from, Object to) {
        hasVertex(from);
        hasVertex(to);
        Optional<Edge> find = actual.findEdge(from, to);
        check("findEdge(%s, %s)", from, to).about(optionals()).that(find).isPresent();
        return assertAbout(edges()).that(find.orElse(null));
    }

    public EdgeSubject hasEdge(Object id) {
        Optional<Edge> find = actual.findEdge(id);
        check("findEdge(%s)", id).about(optionals()).that(find).isPresent();
        return assertAbout(edges()).that(find.orElse(null));
    }

    public void hasNoEdge(Object from, Object to) {
        var find = actual.findEdge(from, to);
        check("findEdge(%s, %s)", from, to).about(optionals()).that(find).isEmpty();
    }

    public void hasNoEdge(Object id) {
        var find =actual.findEdge(id);
        check("findEdge(%s)", id).about(optionals()).that(find).isEmpty();
    }

    public VertexSubject hasVertex(Object id) {
        Optional<Vertex> find = actual.findVertex(id);
        check("findVertex(%s)", id).about(optionals()).that(find).isPresent();
        return assertAbout(vertices()).that(find.orElse(null));
    }

    public void hasNoVertex(Object id) {
        var find = actual.findVertex(id);
        check("findVertex(%s)", id).about(optionals()).that(find).isEmpty();
    }

    public void hasVertices(String id, String... ids) {
        hasVertex(id);
        for(String i : ids) {
            hasVertex(i);
        }
    }

    public void hasExactVertices(String id, String... ids) {
        var set = new HashSet<String>();
        set.add(id);
        for(String i : ids) {
            if(!set.add(id)) {
                failWithActual(simpleFact("duplicate hasIdThat in arguments."));
            }
        }
        var keys = actual.getVertices().keySet();
        if(set.size() != keys.size()) {
            failWithActual(simpleFact("Expected graph with size " + set.size() + " but found was size " + keys.size() ));
        }
        var removed = new HashSet<String>();
        for(String s : set) {
            if(keys.remove(s)) {
                removed.add(s);
            }
        }
        set.removeAll(removed);
        if(set.size() != 0) {
            //missing vertices
        }
        if(keys.size() != 0) {
            //graph has other vertices
        }
    }

    public void isDirected() {
        if(!actual.isDirected()) {
            failWithActual(simpleFact("Expected directed graph."));
        }
    }

    public void isEmpty() {
        if(!actual.isEmpty()) {
            failWithActual(simpleFact("Expected empty graph."));
        }
    }

    public void isNotDirected() {
        if(actual.isDirected()) {
            failWithActual(simpleFact("Expected undirected graph."));
        }
    }

    public OptionalSubject withProperty(String name) {
        return check("getProperty(%s)", name).about(optionals()).that(actual.findProperty(name));
    }

    public OptionalSubject withEdgeProperty(String name) {
        return check("getEdgeProperty(%s)", name).about(optionals()).that(actual.findEdgeProperty(name));
    }

    public OptionalSubject withVertexProperty(String name) {
        return check("getVertexProperty(%s)", name).about(optionals()).that(actual.findVertexProperty(name));
    }
}
