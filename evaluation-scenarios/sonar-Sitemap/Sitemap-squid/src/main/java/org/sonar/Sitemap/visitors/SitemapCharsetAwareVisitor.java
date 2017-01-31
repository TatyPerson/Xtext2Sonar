package org.sonar.Sitemap.visitors;

import java.nio.charset.Charset;

public interface SitemapCharsetAwareVisitor {

  void setCharset(Charset charset);

}
