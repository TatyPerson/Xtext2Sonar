package org.sslr.NAME.toolkit;

import org.sonar.sslr.toolkit.Toolkit;

public final class NAMEToolkit {

  private NAMEToolkit() {
  }

  public static void main(String[] args) {
    Toolkit toolkit = new Toolkit("SSLR :: NAME :: Toolkit", new NAMEConfigurationModel());
    toolkit.run();
  }

}
