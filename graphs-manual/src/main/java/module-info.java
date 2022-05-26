module com.github.moaxcp.graphs.manual {
  exports com.github.moaxcp.graphs.manual;
  requires static lombok;
  requires com.github.moaxcp.graphs.core;
  requires transitive com.github.moaxcp.graphs.graphviz;
  requires transitive com.github.moaxcp.graphs.greenrobot;
  requires transitive com.github.moaxcp.graphs.truth;
}