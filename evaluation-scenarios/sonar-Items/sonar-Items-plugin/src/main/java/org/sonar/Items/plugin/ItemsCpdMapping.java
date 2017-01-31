package org.sonar.Items.plugin;

import java.nio.charset.Charset;

import net.sourceforge.pmd.cpd.Tokenizer;

import org.sonar.api.batch.AbstractCpdMapping;
import org.sonar.api.resources.Language;
import org.sonar.api.batch.fs.FileSystem;

public class ItemsCpdMapping extends AbstractCpdMapping {

  private final ItemsLanguage language;
  private final Charset charset;

  public ItemsCpdMapping(ItemsLanguage language, FileSystem fs) {
    this.language = language;
    this.charset = fs.encoding();
  }

  public Tokenizer getTokenizer() {
    return new ItemsTokenizer(charset);
  }

  public Language getLanguage() {
    return language;
  }

}
