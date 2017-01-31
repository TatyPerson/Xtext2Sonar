package org.sslr.Thing.toolkit;

import org.sonar.sslr.toolkit.Toolkit;

public final class ThingToolkit {

  private ThingToolkit() {
  }

  public static void main(String[] args) {
    Toolkit toolkit = new Toolkit("SSLR :: Thing :: Toolkit", new ThingConfigurationModel());
    toolkit.run();
  }

}
