package org.sonar.Sitemap.plugin;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import net.sourceforge.pmd.cpd.SourceCode;
import net.sourceforge.pmd.cpd.TokenEntry;
import net.sourceforge.pmd.cpd.Tokenizer;
import net.sourceforge.pmd.cpd.Tokens;

import org.sonar.Sitemap.SitemapConfiguration;
import org.sonar.Sitemap.lexer.SitemapLexer;

import com.sonar.sslr.api.Token;
import com.sonar.sslr.impl.Lexer;

public class SitemapTokenizer implements Tokenizer {

  private final Charset charset;

  public SitemapTokenizer(Charset charset) {
    this.charset = charset;
  }

  public final void tokenize(SourceCode source, Tokens cpdTokens) {
    Lexer lexer = SitemapLexer.create(new SitemapConfiguration(charset));
    String fileName = source.getFileName();
    List<Token> tokens = lexer.lex(new File(fileName));
    for (Token token : tokens) {
      TokenEntry cpdToken = new TokenEntry(getTokenImage(token), fileName, token.getLine());
      cpdTokens.add(cpdToken);
    }
    cpdTokens.add(TokenEntry.getEOF());
  }

  private String getTokenImage(Token token) {
    return token.getValue();
  }

}
