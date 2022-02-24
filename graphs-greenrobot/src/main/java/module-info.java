module com.github.moaxcp.graphs.greenrobot {
    requires static lombok;
    exports com.github.moaxcp.graphs.greenrobot;
    requires transitive com.github.moaxcp.graphs.core;
    requires transitive eventbus.java;
}