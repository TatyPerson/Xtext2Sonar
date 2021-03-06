package org.sonar.Sculptordsl.plugin;

import java.nio.charset.Charset;

import net.sourceforge.pmd.cpd.Tokenizer;

import org.sonar.api.batch.AbstractCpdMapping;
import org.sonar.api.resources.Language;
import org.sonar.api.batch.fs.FileSystem;

public class SculptordslCpdMapping extends AbstractCpdMapping {

  private final SculptordslLanguage language;
  private final Charset charset;

  public SculptordslCpdMapping(SculptordslLanguage language, FileSystem fs) {
    this.language = language;
    this.charset = fs.encoding();
  }

  public Tokenizer getTokenizer() {
    return new SculptordslTokenizer(charset);
  }

  public Language getLanguage() {
    return language;
  }

}
