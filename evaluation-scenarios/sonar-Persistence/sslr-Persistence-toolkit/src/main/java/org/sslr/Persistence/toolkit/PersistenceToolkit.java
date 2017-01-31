package org.sslr.Persistence.toolkit;

import org.sonar.sslr.toolkit.Toolkit;

public final class PersistenceToolkit {

  private PersistenceToolkit() {
  }

  public static void main(String[] args) {
    Toolkit toolkit = new Toolkit("SSLR :: Persistence :: Toolkit", new PersistenceConfigurationModel());
    toolkit.run();
  }

}
