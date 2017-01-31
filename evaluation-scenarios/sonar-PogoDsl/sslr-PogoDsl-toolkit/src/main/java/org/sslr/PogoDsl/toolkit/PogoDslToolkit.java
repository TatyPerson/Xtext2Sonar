package org.sslr.PogoDsl.toolkit;

import org.sonar.sslr.toolkit.Toolkit;

public final class PogoDslToolkit {

  private PogoDslToolkit() {
  }

  public static void main(String[] args) {
    Toolkit toolkit = new Toolkit("SSLR :: PogoDsl :: Toolkit", new PogoDslConfigurationModel());
    toolkit.run();
  }

}
