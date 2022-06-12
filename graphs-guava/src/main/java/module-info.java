module com.github.moaxcp.graphs.guava {
    requires static lombok;
    exports com.github.moaxcp.graphs.guava;
    requires transitive com.github.moaxcp.graphs.core;
    requires transitive com.google.common;
}