package org.sonar.Items.plugin.highlighter;

import com.sonar.sslr.api.Token;
import com.sonar.sslr.api.TokenType;
import com.sonar.sslr.api.Trivia;
import com.sonar.sslr.impl.Lexer;
import org.sonar.api.source.Highlightable;
import org.sonar.Items.ItemsConfiguration;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import org.sonar.Items.api.ItemsKeyword;
import org.sonar.Items.api.ItemsTokenType;
import org.sonar.Items.lexer.ItemsLexer;

/* for CSS see https://github.com/SonarSource/sonarqube/blob/master/sonar-colorizer/src/main/resources/sonar-colorizer.css */
public class ItemsHighlighter {

  private Lexer lexer;
  private Charset charset;

  public ItemsHighlighter(ItemsConfiguration conf) {
    this.lexer = ItemsLexer.create(conf);
    this.charset = conf.getCharset();
  }

  public void highlight(Highlightable highlightable, File file) {
    SourceFileOffsets offsets = new SourceFileOffsets(file, charset);
    List<Token> tokens = lexer.lex(file);
    doHighlight(highlightable, tokens, offsets);
  }

  public void highlight(Highlightable highlightable, String string) {
    SourceFileOffsets offsets = new SourceFileOffsets(string);
    List<Token> tokens = lexer.lex(string);
    doHighlight(highlightable, tokens, offsets);
  }

  private void doHighlight(Highlightable highlightable, List<Token> tokens, SourceFileOffsets offsets) {
    Highlightable.HighlightingBuilder highlighting = highlightable.newHighlighting();
    highlightNonComments(highlighting, tokens, offsets);
    highlightComments(highlighting, tokens, offsets);
    highlighting.done();
  }

  private void highlightComments(Highlightable.HighlightingBuilder highlighting, List<Token> tokens, SourceFileOffsets offsets) {
    String code;
    for (Token token : tokens) {
      if (!token.getTrivia().isEmpty()) {
        for (Trivia trivia : token.getTrivia()) {
          if (trivia.getToken().getValue().startsWith("/**")) {
            code = "j"; // javadoc
          } else {
            code = "cppd"; // C++ doc
          }
          highlight(highlighting, offsets.startOffset(trivia.getToken()), offsets.endOffset(trivia.getToken()), code);
        }
      }
    }
  }

  private void highlightNonComments(Highlightable.HighlightingBuilder highlighting, List<Token> tokens, SourceFileOffsets offsets) {
    for (Token token : tokens) {
      if (ItemsTokenType.STRING.equals(token.getType())) {
        highlight(highlighting, offsets.startOffset(token), offsets.endOffset(token), "s"); // string
      }
      if (isConstant(token.getType())) {
        highlight(highlighting, offsets.startOffset(token), offsets.endOffset(token), "c"); // constants
      }
      if (isKeyword(token.getType())) {
        highlight(highlighting, offsets.startOffset(token), offsets.endOffset(token), "k"); // keyword
      }
    }
  }

  private static void highlight(Highlightable.HighlightingBuilder highlighting, int startOffset, int endOffset, String code) {
    if (endOffset > startOffset) {
      highlighting.highlight(startOffset, endOffset, code);
    }
  }

  private boolean isConstant(TokenType type) {
    return ItemsTokenType.NUMBER.equals(type)
      || ItemsTokenType.CHARACTER.equals(type);
  }

  private boolean isKeyword(TokenType type) {
    for (TokenType keywordType : ItemsKeyword.values()) {
      if (keywordType.equals(type)) {
        return true;
      }
    }
    return false;
  }
}
