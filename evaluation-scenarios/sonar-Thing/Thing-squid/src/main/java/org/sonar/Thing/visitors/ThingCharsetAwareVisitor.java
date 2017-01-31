package org.sonar.Thing.visitors;

import java.nio.charset.Charset;

public interface ThingCharsetAwareVisitor {

  void setCharset(Charset charset);

}
