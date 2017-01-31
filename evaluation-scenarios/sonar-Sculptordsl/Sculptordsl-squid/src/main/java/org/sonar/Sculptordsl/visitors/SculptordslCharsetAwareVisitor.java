package org.sonar.Sculptordsl.visitors;

import java.nio.charset.Charset;

public interface SculptordslCharsetAwareVisitor {

  void setCharset(Charset charset);

}
