package org.sonar.Sculptordsl.channels;

import org.sonar.Sculptordsl.api.SculptordslTokenType;
import org.sonar.sslr.channel.Channel;
import org.sonar.sslr.channel.CodeReader;

import com.sonar.sslr.api.Token;
import com.sonar.sslr.impl.Lexer;

/**
  */
public class StringLiteralsChannel extends Channel<Lexer> {

  private static final char EOF = (char) -1;

  private final StringBuilder sb = new StringBuilder();

  private int index;
  private char ch;

  @Override
  public boolean consume(CodeReader code, Lexer output) {
    int line = code.getLinePosition();
    int column = code.getColumnPosition();
    index = 0;
    readStringPrefix(code);
    if ((ch != '\"')) {
      return false;
    }
    if (!read(code)) {
      return false;
    }
    for (int i = 0; i < index; i++) {
      sb.append((char) code.pop());
    }
    output.addToken(Token.builder()
        .setLine(line)
        .setColumn(column)
        .setURI(output.getURI())
        .setValueAndOriginalValue(sb.toString())
        .setType(SculptordslTokenType.STRING)
        .build());
    sb.setLength(0);
    return true;
  }

  private boolean read(CodeReader code) {
    // TODO: proper reading raw strings.

    index++;
    while (code.charAt(index) != ch) {
      if (code.charAt(index) == EOF) {
        return false;
      }
      if (code.charAt(index) == '\\') {
        // escape
        index++;
      }
      index++;
    }
    index++;
    return true;
  }

  private void readStringPrefix(CodeReader code) {
    ch = code.charAt(index);
    if ((ch == 'u') || (ch == 'U') || ch == 'L') {
      index++;
      if (ch == 'u' && code.charAt(index) == '8') {
        index++;
      }
      ch = code.charAt(index);
    }
    if (ch == 'R') {
      index++;
      ch = code.charAt(index);
    }
  }
}
