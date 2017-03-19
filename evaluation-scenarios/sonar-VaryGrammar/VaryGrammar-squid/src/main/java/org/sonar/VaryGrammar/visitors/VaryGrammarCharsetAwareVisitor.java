package org.sonar.VaryGrammar.visitors;

import java.nio.charset.Charset;

public interface VaryGrammarCharsetAwareVisitor {

  void setCharset(Charset charset);

}
