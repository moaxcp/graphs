package com.github.moaxcp.graphs.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.Module;

public class GraphsModule extends Module {
  @Override
  public String getModuleName() {
    return "GraphsModule";
  }

  @Override
  public Version version() {
    return PackageVersion.VERSION;
  }

  @Override
  public void setupModule(SetupContext context) {
    context.addDeserializers(new GraphDeserializers());
    context.addSerializers(new GraphSerializers());
  }
}
