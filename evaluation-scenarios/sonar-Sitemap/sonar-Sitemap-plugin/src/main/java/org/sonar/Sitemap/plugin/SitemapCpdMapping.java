package org.sonar.Sitemap.plugin;

import java.nio.charset.Charset;

import net.sourceforge.pmd.cpd.Tokenizer;

import org.sonar.api.batch.AbstractCpdMapping;
import org.sonar.api.resources.Language;
import org.sonar.api.batch.fs.FileSystem;

public class SitemapCpdMapping extends AbstractCpdMapping {

  private final SitemapLanguage language;
  private final Charset charset;

  public SitemapCpdMapping(SitemapLanguage language, FileSystem fs) {
    this.language = language;
    this.charset = fs.encoding();
  }

  public Tokenizer getTokenizer() {
    return new SitemapTokenizer(charset);
  }

  public Language getLanguage() {
    return language;
  }

}
