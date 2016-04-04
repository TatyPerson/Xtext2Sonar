package org.sonar.NAME.plugin;

import java.nio.charset.Charset;

import net.sourceforge.pmd.cpd.Tokenizer;

import org.sonar.api.batch.AbstractCpdMapping;
import org.sonar.api.resources.Language;
import org.sonar.api.batch.fs.FileSystem;

public class NAMECpdMapping extends AbstractCpdMapping {

  private final NAMELanguage language;
  private final Charset charset;

  public NAMECpdMapping(NAMELanguage language, FileSystem fs) {
    this.language = language;
    this.charset = fs.encoding();
  }

  public Tokenizer getTokenizer() {
    return new NAMETokenizer(charset);
  }

  public Language getLanguage() {
    return language;
  }

}
