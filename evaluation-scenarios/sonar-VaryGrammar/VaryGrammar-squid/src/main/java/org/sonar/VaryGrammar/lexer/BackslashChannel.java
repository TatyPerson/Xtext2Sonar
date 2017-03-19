package org.sonar.VaryGrammar.lexer;

import org.sonar.sslr.channel.Channel;
import org.sonar.sslr.channel.CodeReader;

import com.sonar.sslr.impl.Lexer;

public class BackslashChannel extends Channel<Lexer> {
  @Override
  public boolean consume(CodeReader code, Lexer output) {
    char ch = (char) code.peek();

    if ((ch == '\\') && isNewLine(code.charAt(1))) {
      // just throw away the backslash
      code.pop();
      return true;
    }

    return false;
  }

  private static boolean isNewLine(char ch) {
    return (ch == '\n') || (ch == '\r');
  }
}
