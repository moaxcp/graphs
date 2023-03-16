package com.github.moaxcp.graphs.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;

public class GraphsModule extends Module {
  @Override
  public String getModuleName() {
    return null;
  }

  @Override
  public Version version() {
    return null;
  }

  @Override
  public void setupModule(SetupContext context) {

  }
}
