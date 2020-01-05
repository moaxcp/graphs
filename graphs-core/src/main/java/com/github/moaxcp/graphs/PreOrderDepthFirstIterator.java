package com.github.moaxcp.graphs;

import lombok.*;

import java.util.*;

class PreOrderDepthFirstIterator<ID> extends DepthFirstIterator<ID> {

  PreOrderDepthFirstIterator(@NonNull Graph<ID> graph, @NonNull ID... start) {
    super(graph, start);
  }

  void goToNextVertex() {
    if(getCurrentId() != null) {
      List<ID> traverse = getTraverseVertices(getCurrentId());
      //add to stack in reverse to maintain insertion order for Linked sets
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
