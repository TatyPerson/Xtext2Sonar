package org.sslr.Sitemap.toolkit;

import org.sonar.sslr.toolkit.Toolkit;

public final class SitemapToolkit {

  private SitemapToolkit() {
  }

  public static void main(String[] args) {
    Toolkit toolkit = new Toolkit("SSLR :: Sitemap :: Toolkit", new SitemapConfigurationModel());
    toolkit.run();
  }

}
