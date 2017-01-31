package org.sslr.Sculptordsl.toolkit;

import org.sonar.sslr.toolkit.Toolkit;

public final class SculptordslToolkit {

  private SculptordslToolkit() {
  }

  public static void main(String[] args) {
    Toolkit toolkit = new Toolkit("SSLR :: Sculptordsl :: Toolkit", new SculptordslConfigurationModel());
    toolkit.run();
  }

}
