package org.sonar.LilyPond.plugin;

import java.nio.charset.Charset;

import net.sourceforge.pmd.cpd.Tokenizer;

import org.sonar.api.batch.AbstractCpdMapping;
import org.sonar.api.resources.Language;
import org.sonar.api.batch.fs.FileSystem;

public class LilyPondCpdMapping extends AbstractCpdMapping {

  private final LilyPondLanguage language;
  private final Charset charset;

  public LilyPondCpdMapping(LilyPondLanguage language, FileSystem fs) {
    this.language = language;
    this.charset = fs.encoding();
  }

  public Tokenizer getTokenizer() {
    return new LilyPondTokenizer(charset);
  }

  public Language getLanguage() {
    return language;
  }

}
