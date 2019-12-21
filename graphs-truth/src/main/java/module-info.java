module com.github.moaxcp.graphs.truth {
    requires static lombok;
    exports com.github.moaxcp.graphs.truth;
    requires com.github.moaxcp.graphs.core;
    requires com.github.moaxcp.graphs.greenrobot;
    requires truth;
    requires org.checkerframework.checker.qual;
    requires eventbus;
}