package com.github.moaxcp.graphs.truth;

import com.github.moaxcp.graphs.PropertyGraph.Edge;
import com.google.common.truth.FailureMetadata;
import com.google.common.truth.MapSubject;
import com.google.common.truth.OptionalSubject;
import com.google.common.truth.Subject;
import java.util.Optional;

import static com.google.common.truth.Fact.simpleFact;
import static com.google.common.truth.OptionalSubject.optionals;
import static com.google.common.truth.Truth.assertAbout;

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

  public MapSubject withLocal() {
    return check("local()").that(actual.local());
  }

  public void isDirected() {
    if (!actual.isDirected()) {
      failWithActual(simpleFact("Expected directed edge"));
    }
  }

  public void isUndirected() {
    if (actual.isDirected()) {
      failWithActual(simpleFact("Expected undirected edge"));
    }
  }
}
