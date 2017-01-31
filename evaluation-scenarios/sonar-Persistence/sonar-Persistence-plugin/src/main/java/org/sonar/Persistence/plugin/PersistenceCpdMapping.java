package org.sonar.Persistence.plugin;

import java.nio.charset.Charset;

import net.sourceforge.pmd.cpd.Tokenizer;

import org.sonar.api.batch.AbstractCpdMapping;
import org.sonar.api.resources.Language;
import org.sonar.api.batch.fs.FileSystem;

public class PersistenceCpdMapping extends AbstractCpdMapping {

  private final PersistenceLanguage language;
  private final Charset charset;

  public PersistenceCpdMapping(PersistenceLanguage language, FileSystem fs) {
    this.language = language;
    this.charset = fs.encoding();
  }

  public Tokenizer getTokenizer() {
    return new PersistenceTokenizer(charset);
  }

  public Language getLanguage() {
    return language;
  }

}
