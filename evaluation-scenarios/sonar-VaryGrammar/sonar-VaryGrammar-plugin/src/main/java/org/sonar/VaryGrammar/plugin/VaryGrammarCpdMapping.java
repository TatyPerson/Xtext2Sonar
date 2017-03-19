package org.sonar.VaryGrammar.plugin;

import java.nio.charset.Charset;

import net.sourceforge.pmd.cpd.Tokenizer;

import org.sonar.api.batch.AbstractCpdMapping;
import org.sonar.api.resources.Language;
import org.sonar.api.batch.fs.FileSystem;

public class VaryGrammarCpdMapping extends AbstractCpdMapping {

  private final VaryGrammarLanguage language;
  private final Charset charset;

  public VaryGrammarCpdMapping(VaryGrammarLanguage language, FileSystem fs) {
    this.language = language;
    this.charset = fs.encoding();
  }

  public Tokenizer getTokenizer() {
    return new VaryGrammarTokenizer(charset);
  }

  public Language getLanguage() {
    return language;
  }

}
