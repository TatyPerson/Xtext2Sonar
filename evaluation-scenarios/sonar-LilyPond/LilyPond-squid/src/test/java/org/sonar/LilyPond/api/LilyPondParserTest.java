package org.sonar.LilyPond.api;

import org.junit.Test;
import static org.sonar.sslr.tests.Assertions.assertThat;

public class LilyPondParserTest extends ParserBaseTest {
	
	  @Test
	  public void test_version() {
		  p.setRootRule(g.rule(LilyPondImpl.VERSION));
		  assertThat(p).matches("\\version \"2.18.0\"");
	  }
	  
	  @Test
	  public void test_header_empty() {
		  p.setRootRule(g.rule(LilyPondImpl.BLOCKCOMMAND));
		  assertThat(p).matches("\\header {" + "\n" + "}");
	  }
	  
	  @Test
	  public void test_header() {
		  p.setRootRule(g.rule(LilyPondImpl.BLOCKCOMMAND));
		  assertThat(p).matches("\\header {" + "\n" + "tagline = \"\"" + "\n" + "}");
	  }
	  
	  @Test
	  public void test_score() {
		  p.setRootRule(g.rule(LilyPondImpl.BLOCKCOMMAND));
		  assertThat(p).matches("\\score {" + "\n" + "\\new Staff \\music" + "\n" + "\\layout {}" + "\\midi {}" + "\n" + "}");
	  }
	  
	  @Test
	  public void test_score_error() {
		  p.setRootRule(g.rule(LilyPondImpl.BLOCKCOMMAND));
		  assertThat(p).notMatches("\\ERROR {" + "\n" + "\\new Staff \\music" + "\n" + "\\layout {}" + "\\midi {}" + "\n" + "}");
	  }
	  
	  @Test
	  public void test_relative_empty() {
		  p.setRootRule(g.rule(LilyPondImpl.RELATIVEMUSIC));
		  assertThat(p).matches("\\relative {" + "\n" + "}");
	  }
	  
	  @Test
	  public void test_pitch() {
		  p.setRootRule(g.rule(LilyPondImpl.PITCH));
		  assertThat(p).matches("c'");
	  }
	  
	  @Test
	  public void test_relative_pitch() {
		  p.setRootRule(g.rule(LilyPondImpl.RELATIVEMUSIC));
		  assertThat(p).matches("\\relative c' {" + "\n" + "}");
	  }
	  
	  @Test
	  public void test_relative_pitch_block() {
		  p.setRootRule(g.rule(LilyPondImpl.RELATIVEMUSIC));
		  assertThat(p).matches("\\relative c' {" + "\n" + "c c c' g g |" + "\n" + "a a g2 |" + "\n" + "f4 f e e |" + "\n" + "d d8. e16 c2 | \\bar \"|.\"" + "\n" + "}");
	  }
}
