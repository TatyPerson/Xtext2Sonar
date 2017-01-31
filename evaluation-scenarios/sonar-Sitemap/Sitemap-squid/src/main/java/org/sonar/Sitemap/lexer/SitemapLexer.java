package org.sonar.Sitemap.lexer;

import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.ANY_CHAR;
import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.and;
import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.commentRegexp;
import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.o2n;
import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.opt;
import static com.sonar.sslr.impl.channel.RegexpChannelBuilder.regexp;

import org.sonar.Sitemap.SitemapConfiguration;
import org.sonar.Sitemap.api.SitemapTokenType;
import org.sonar.Sitemap.channels.CharacterLiteralsChannel;
import org.sonar.Sitemap.channels.StringLiteralsChannel;
import org.sonar.Sitemap.api.SitemapKeyword;
import org.sonar.Sitemap.api.SitemapPunctuator;

import com.sonar.sslr.impl.Lexer;
import com.sonar.sslr.impl.channel.BlackHoleChannel;
import com.sonar.sslr.impl.channel.IdentifierAndKeywordChannel;
import com.sonar.sslr.impl.channel.PunctuatorChannel;
import com.sonar.sslr.impl.channel.UnknownCharacterChannel;

public final class SitemapLexer {

  private SitemapLexer() {
  }

  public static Lexer create() {
    return create(new SitemapConfiguration());
  }

  private static final String INTEGER_SUFFIX = "(((U|u)(LL|ll|L|l)?)|((LL|ll|L|l)(u|U)?))";
  private static final String EXP = "([Ee][+-]?+[0-9_]++)";
  private static final String FLOAT_SUFFIX = "(f|l|F|L)";

  public static Lexer create(SitemapConfiguration conf) {
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
        .withChannel(regexp(SitemapTokenType.NUMBER, "[0-9]++\\.[0-9]*+" + opt(EXP) + opt(FLOAT_SUFFIX)))
        .withChannel(regexp(SitemapTokenType.NUMBER, "\\.[0-9]++" + opt(EXP) + opt(FLOAT_SUFFIX)))
        .withChannel(regexp(SitemapTokenType.NUMBER, "[0-9]++" + EXP + opt(FLOAT_SUFFIX)))

        // C++ Standard, Section 2.14.2 "Integer literals"
        .withChannel(regexp(SitemapTokenType.NUMBER, "[1-9][0-9]*+" + opt(INTEGER_SUFFIX))) // Decimal literals
        .withChannel(regexp(SitemapTokenType.NUMBER, "0[0-7]++" + opt(INTEGER_SUFFIX))) // Octal Literals
        .withChannel(regexp(SitemapTokenType.NUMBER, "0[xX][0-9a-fA-F]++" + opt(INTEGER_SUFFIX))) // Hex Literals
        .withChannel(regexp(SitemapTokenType.NUMBER, "0" + opt(INTEGER_SUFFIX))) // Decimal zero

        // TODO:
        // C++ Standard, Section 2.14.8 "User-defined literals"

        // C++ Standard, Section 2.12 "Keywords"
        // C++ Standard, Section 2.11 "Identifiers"
        .withChannel(new IdentifierAndKeywordChannel(and("[a-zA-Z_]", o2n("\\w")), true, SitemapKeyword.values()))

        // C++ Standard, Section 2.13 "Operators and punctuators"
        .withChannel(new PunctuatorChannel(SitemapPunctuator.values()))

        .withChannel(new UnknownCharacterChannel());

    return builder.build();
  }
}
