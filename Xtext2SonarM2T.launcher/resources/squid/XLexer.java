package org.sonar.NAME.lexer;

import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.ANY_CHAR;
import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.and;
import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.commentRegexp;
import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.o2n;
import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.opt;
import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.regexp;

import org.sonar.NAME.NAMEConfiguration;
import org.sonar.NAME.api.NAMETokenType;
import org.sonar.NAME.channels.CharacterLiteralsChannel;
import org.sonar.NAME.channels.StringLiteralsChannel;
import org.sonar.NAME.api.NAMEKeyword;
import org.sonar.NAME.api.NAMEPunctuator;

import com.sonar.sslr.impl.Lexer;
import com.sonar.sslr.impl.channel.BlackHoleChannel;
import com.sonar.sslr.impl.channel.IdentifierAndKeywordChannel;
import com.sonar.sslr.impl.channel.PunctuatorChannel;
import com.sonar.sslr.impl.channel.UnknownCharacterChannel;

public final class NAMELexer {

  private NAMELexer() {
  }

  public static Lexer create() {
    return create(new NAMEConfiguration());
  }

  private static final String INTEGER_SUFFIX = "(((U|u)(LL|ll|L|l)?)|((LL|ll|L|l)(u|U)?))";
  private static final String EXP = "([Ee][+-]?+[0-9_]++)";
  private static final String FLOAT_SUFFIX = "(f|l|F|L)";

  public static Lexer create(NAMEConfiguration conf) {
    Lexer.Builder builder = Lexer.builder()
        .withCharset(conf.getCharset())
        .withFailIfNoChannelToConsumeOneCharacter(true)

        .withChannel(new BlackHoleChannel("\\s"))
        
        // Comments
        // C++ Standard, Section 2.8 "Comments"
        .withChannel(commentRegexp("//[^\\n\\r]*+"))
        .withChannel(commentRegexp("/\\*", ANY_CHAR + "*?", "\\*/"))

        // backslash at the end of the line: just throw away
        .withChannel(new BackslashChannel())

        // Preprocessor directives
        //.withChannel(new PreprocessorChannel())

        // C++ Standard, Section 2.14.3 "Character literals"
        .withChannel(new CharacterLiteralsChannel())

        // C++ Standard, Section 2.14.5 "String literals"
        .withChannel(new StringLiteralsChannel())

        // C++ Standard, Section 2.14.4 "Floating literals"
        .withChannel(regexp(NAMETokenType.NUMBER, "[0-9]++\\.[0-9]*+" + opt(EXP) + opt(FLOAT_SUFFIX)))
        .withChannel(regexp(NAMETokenType.NUMBER, "\\.[0-9]++" + opt(EXP) + opt(FLOAT_SUFFIX)))
        .withChannel(regexp(NAMETokenType.NUMBER, "[0-9]++" + EXP + opt(FLOAT_SUFFIX)))

        // C++ Standard, Section 2.14.2 "Integer literals"
        .withChannel(regexp(NAMETokenType.NUMBER, "[1-9][0-9]*+" + opt(INTEGER_SUFFIX))) // Decimal literals
        .withChannel(regexp(NAMETokenType.NUMBER, "0[0-7]++" + opt(INTEGER_SUFFIX))) // Octal Literals
        .withChannel(regexp(NAMETokenType.NUMBER, "0[xX][0-9a-fA-F]++" + opt(INTEGER_SUFFIX))) // Hex Literals
        .withChannel(regexp(NAMETokenType.NUMBER, "0" + opt(INTEGER_SUFFIX))) // Decimal zero

        // TODO:
        // C++ Standard, Section 2.14.8 "User-defined literals"

        // C++ Standard, Section 2.12 "Keywords"
        // C++ Standard, Section 2.11 "Identifiers"
        .withChannel(new IdentifierAndKeywordChannel(and("[a-zA-Z_]", o2n("\\w")), true, NAMEKeyword.values()))

        // C++ Standard, Section 2.13 "Operators and punctuators"
        .withChannel(new PunctuatorChannel(NAMEPunctuator.values()))

        .withChannel(new UnknownCharacterChannel());

    return builder.build();
  }
}
