package org.sslr.LilyPond.toolkit;

import org.sonar.sslr.toolkit.Toolkit;

public final class LilyPondToolkit {

  private LilyPondToolkit() {
  }

  public static void main(String[] args) {
    Toolkit toolkit = new Toolkit("SSLR :: LilyPond :: Toolkit", new LilyPondConfigurationModel());
    toolkit.run();
  }

}
