package org.sonar.PogoDsl.visitors;

import java.nio.charset.Charset;

public interface PogoDslCharsetAwareVisitor {

  void setCharset(Charset charset);

}
