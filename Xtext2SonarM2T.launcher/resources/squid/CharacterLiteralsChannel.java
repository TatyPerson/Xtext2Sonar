package org.sonar.NAME.channels;

import org.sonar.NAME.api.NAMETokenType;
import org.sonar.sslr.channel.Channel;
import org.sonar.sslr.channel.CodeReader;

import com.sonar.sslr.api.Token;
import com.sonar.sslr.impl.Lexer;

public class CharacterLiteralsChannel extends Channel<Lexer> {
  private static final char EOF = (char) -1;

  private final StringBuilder sb = new StringBuilder();

  private int index;
  private char ch;

  @Override
  public boolean consume(CodeReader code, Lexer output) {
    int line = code.getLinePosition();
    int column = code.getColumnPosition();
    index = 0;
    readPrefix(code);
    if ((ch != '\'')) {
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
        .setType(NAMETokenType.CHARACTER)
        .build());
    sb.setLength(0);
    return true;
  }

  private boolean read(CodeReader code) {
    index++;
    while (code.charAt(index) != ch) {
      if (code.charAt(index) == EOF || code.charAt(index) == '\n') {
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

  private void readPrefix(CodeReader code) {
    ch = code.charAt(index);
    if ((ch == 'u') || (ch == 'U') || ch == 'L') {
      index++;
      ch = code.charAt(index);
    }
  }
}
