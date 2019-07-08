module com.github.moaxcp.graphs.truth {
    exports com.github.moaxcp.graphs.truth;
    exports com.github.moaxcp.graphs.truth.greenrobot;
    requires com.github.moaxcp.graphs.core;
    requires com.github.moaxcp.graphs.greenrobot;
    requires truth;
    requires checker.compat.qual;
    requires checker.qual;
    requires eventbus;
}