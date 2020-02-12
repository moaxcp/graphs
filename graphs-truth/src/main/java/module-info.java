module com.github.moaxcp.graphs.truth {
    requires static lombok;
    exports com.github.moaxcp.graphs.truth;
    requires transitive com.github.moaxcp.graphs.core;
    requires transitive com.github.moaxcp.graphs.greenrobot;
    requires transitive truth;
    requires transitive org.checkerframework.checker.qual;
    requires transitive eventbus;
}