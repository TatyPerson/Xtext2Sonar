package org.sonar.Items.visitors;

import java.nio.charset.Charset;

public interface ItemsCharsetAwareVisitor {

  void setCharset(Charset charset);

}
