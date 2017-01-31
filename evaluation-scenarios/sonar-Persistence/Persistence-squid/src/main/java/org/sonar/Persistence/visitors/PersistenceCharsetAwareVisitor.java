package org.sonar.Persistence.visitors;

import java.nio.charset.Charset;

public interface PersistenceCharsetAwareVisitor {

  void setCharset(Charset charset);

}
