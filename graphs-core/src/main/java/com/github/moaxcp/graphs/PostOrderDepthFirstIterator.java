package com.github.moaxcp.graphs;

import java.util.*;

class PostOrderDepthFirstIterator<ID> extends DepthFirstIterator<ID> {

  private final Set<ID> complete;

  PostOrderDepthFirstIterator(PropertyGraph<ID> graph, ID... start) {
    super(graph, start);
    complete = new HashSet<>();
  }

  void goToNextVertex() {
    if(getStack().isEmpty()) {
      getStart().ifPresent(getStack()::push);
    }

    if(getStack().isEmpty()) {
      setCurrentId(null);
      return;
    }

    while(!getStack().isEmpty()) {
      ID id = getStack().getFirst();
      if(complete.contains(id)) {
        getStack().pop();
        continue;
      }
      getVisited().add(id);
      List<ID> traverse = getTraverseVertices(id);
      if (traverse.isEmpty()) {
        setCurrentId(getStack().pop());
        complete.add(getCurrentId());
        return;
      }

      //add to stack in reverse to maintain insertion order for Linked sets
      for (int i = traverse.size() - 1; i >= 0; i--) {
        getStack().push(traverse.get(i));
      }
    }

    setCurrentId(null);
  }
}
