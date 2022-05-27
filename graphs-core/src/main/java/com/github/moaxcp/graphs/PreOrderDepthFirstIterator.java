package com.github.moaxcp.graphs;

import java.util.*;

class PreOrderDepthFirstIterator<ID> extends DepthFirstIterator<ID> {

  PreOrderDepthFirstIterator(PropertyGraph<ID> graph, ID... start) {
    super(graph, start);
  }

  void goToNextVertex() {
    if(getCurrentId() != null) {
      List<ID> traverse = getTraverseVertices(getCurrentId());
      //add to stack in reverse to maintain order of edges
      for(int i = traverse.size() - 1; i >= 0; i--) {
        getStack().push(traverse.get(i));
      }
    }

    if(getStack().isEmpty()) {
      getStart().ifPresent(getStack()::push);
    }

    while(!getStack().isEmpty()) {
      setCurrentId(getStack().pop());
      if(!getVisited().contains(getCurrentId())) {
        getVisited().add(getCurrentId());
        return;
      }
    }
    setCurrentId(null);
  }
}
