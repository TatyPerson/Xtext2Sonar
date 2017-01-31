package org.sonar.LilyPond.visitors;

import java.nio.charset.Charset;

public interface LilyPondCharsetAwareVisitor {

  void setCharset(Charset charset);

}
