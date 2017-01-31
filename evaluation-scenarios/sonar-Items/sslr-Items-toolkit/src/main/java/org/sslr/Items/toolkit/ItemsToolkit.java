package org.sslr.Items.toolkit;

import org.sonar.sslr.toolkit.Toolkit;

public final class ItemsToolkit {

  private ItemsToolkit() {
  }

  public static void main(String[] args) {
    Toolkit toolkit = new Toolkit("SSLR :: Items :: Toolkit", new ItemsConfigurationModel());
    toolkit.run();
  }

}
