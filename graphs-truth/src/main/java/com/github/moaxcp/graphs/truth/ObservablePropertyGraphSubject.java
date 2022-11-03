package com.github.moaxcp.graphs.truth;

import com.github.moaxcp.graphs.ObservablePropertyGraph;
import com.google.common.truth.FailureMetadata;
import com.google.common.truth.IterableSubject;
import com.google.common.truth.Subject;
import java.util.function.Consumer;

import static com.google.common.truth.Truth.assertAbout;

public class ObservablePropertyGraphSubject extends PropertyGraphSubject {

  private ObservablePropertyGraph actual;

  ObservablePropertyGraphSubject(FailureMetadata metadata, ObservablePropertyGraph actual) {
    super(metadata, actual);
    this.actual = actual;
  }

  public static Subject.Factory<ObservablePropertyGraphSubject, ObservablePropertyGraph> observableGraphs() {
    return ObservablePropertyGraphSubject::new;
  }

  static ObservablePropertyGraphSubject assertThat(ObservablePropertyGraph actual) {
    return assertAbout(observableGraphs()).that(actual);
  }

  public IterableSubject withAction(Consumer<ObservablePropertyGraph> action) {
    var observer = new Observer<>();
    actual.addObserver(observer);
    action.accept(actual);
    actual.removeObserver(observer);
    return check("events").that(observer.getEvents());
  }
}
