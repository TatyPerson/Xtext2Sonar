package org.sslr.VaryGrammar.toolkit;

import org.sonar.sslr.toolkit.Toolkit;

public final class VaryGrammarToolkit {

  private VaryGrammarToolkit() {
  }

  public static void main(String[] args) {
    Toolkit toolkit = new Toolkit("SSLR :: VaryGrammar :: Toolkit", new VaryGrammarConfigurationModel());
    toolkit.run();
  }

}
