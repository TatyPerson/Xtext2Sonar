package org.sonar.Thing.plugin;

import java.nio.charset.Charset;

import net.sourceforge.pmd.cpd.Tokenizer;

import org.sonar.api.batch.AbstractCpdMapping;
import org.sonar.api.resources.Language;
import org.sonar.api.batch.fs.FileSystem;

public class ThingCpdMapping extends AbstractCpdMapping {

  private final ThingLanguage language;
  private final Charset charset;

  public ThingCpdMapping(ThingLanguage language, FileSystem fs) {
    this.language = language;
    this.charset = fs.encoding();
  }

  public Tokenizer getTokenizer() {
    return new ThingTokenizer(charset);
  }

  public Language getLanguage() {
    return language;
  }

}
